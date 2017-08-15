package com.epam.inote.dao;

import com.epam.inote.exception.INoteException;
import com.epam.inote.model.Client;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by User on 03.08.2017.
 *
 */
public class ClientDAO extends AbstractDao<Object> {
    private final Logger logger = Logger.getLogger("ClientDAO");
    private final String INSERT = "INSERT INTO client(login,password,email) VALUES (?,?,?)";
    private final String CHECK = "SELECT * FROM client WHERE login=? AND password=?";

    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean add(Client client) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(ONE, client.getLogin());
            ps.setString(TWO, client.getPassword());
            ps.setString(THREE, client.getEmail());
            logger.info(CLIENT+" "+client.getLogin()+" "+ADDED);
            return ps.execute();

        } catch (SQLException e) {
            logger.warn(CLIENT+" "+client.getLogin()+" "+ERROR);
            return false;
        }
    }

    public Client check(String login, String password) {
        Client client = Client.EMPTY;
        try (PreparedStatement ps = connection.prepareStatement(CHECK)) {
            ps.setString(ONE, login);
            ps.setString(TWO, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            client = new Client(rs.getString(LOGIN),
                    rs.getString(PASSWORD),
                    rs.getString(EMAIL));
            client.setId(rs.getInt(ID));
            logger.info(CLIENT+" "+client.getLogin()+" "+LOGIN);
            return client;

        } catch (SQLException e) {
            logger.warn(CLIENT+" "+LOGIN+" "+ERROR);
            return client;
        }
    }


    @Override
    void insert(User user) {
        throw  new UnsupportedOperationException();
    }

    @Override
    User select(Object id) {
        throw  new UnsupportedOperationException();
    }

    @Override
    void update(User user) {
        throw  new UnsupportedOperationException();
    }

    @Override
    void delete(Object id) {
        throw  new UnsupportedOperationException();
    }
}
