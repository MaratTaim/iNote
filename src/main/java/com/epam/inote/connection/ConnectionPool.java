package com.epam.inote.connection;

/**
 * Marat Taimagambetov
 * 15.07.2017
 */

import com.epam.inote.constant.Const;
import com.epam.inote.exception.INoteException;
import com.epam.inote.resource.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool extends Const {
    private static ConnectionPool instance;
    private ConnectionManager manager = new ConnectionManager();
    private final Logger LOGGER = Logger.getLogger("ConnectionPool");
    private int maxConn = Integer.parseInt(manager.getProperty("max.conn"));
    private BlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<Connection>(maxConn);
    private Properties connInfo = new Properties();
    private final String URL = manager.getProperty("url");
    private final String C_USER = manager.getProperty("user");
    private final String C_PASSWORD = manager.getProperty("password");

    private ConnectionPool() {
        loadDrivers();
    }

    private void loadDrivers() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (Exception e) {
            LOGGER.error(CAN_T_REGISTER,e);
            throw new INoteException(CAN_T_REGISTER,e);
        }
    }

    static synchronized public ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection con;
        if (!freeConnections.isEmpty()) {
            con = freeConnections.poll();
            try {
                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (Exception e) {
                con = getConnection();
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {
        Connection con;
        try {
            connInfo.put(USER, C_USER);
            connInfo.put(PASSWORD, C_PASSWORD);
            connInfo.put(CHAR_ENCOD, ENCOD_CP);

            con = DriverManager.getConnection(URL, connInfo);

        } catch (SQLException e) {
            LOGGER.error(CAN_T_CREATE + URL,e);
            throw new INoteException(CAN_T_CREATE + URL,e);
        }
        return con;
    }

    public synchronized void freeConnection(Connection con) {
        if ((con != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(con);
        }
    }

    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = (Connection) allConnections.next();
            try {
                con.close();
            } catch (SQLException e) {
                LOGGER.error(CAN_T_CLOSE,e);
                throw new INoteException(CAN_T_CLOSE,e);
            }
        }
        freeConnections.clear();
    }
}
