package com.epam.inote.dao;

import com.epam.inote.constant.Const;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 01.08.2017.
 */
abstract class AbstractDao<T> extends Const {
    Logger logger = Logger.getLogger("AbstractDao");

    abstract void insert(User user);

    abstract User select(T id);

    abstract void update(User user);

    abstract void delete(T id);

    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (Exception e) {
            logger.error(COMMIT+" "+ERROR, e);
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(ROLLBACK+" "+ERROR, e);
                throw new INoteException(ROLLBACK+" "+ERROR, e);
            }
        }
    }
}
