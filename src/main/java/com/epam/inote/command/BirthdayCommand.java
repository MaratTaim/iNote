package com.epam.inote.command;

import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by User on 25.07.2017.
 * find birthdays of All Users
 */
public class BirthdayCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(BIRTHDAY_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(connection);
            LocalDate date = LocalDate.now();

            Collection<User> collection = userDAO.getBirthday(date.getDayOfMonth() - 1, date.getMonthValue());

            pool.freeConnection(connection);

            request.setAttribute(LIST, collection);
        } catch (Exception e) {
            LOGGER.error(EX_ERROR,e);
            throw new INoteException(EX_ERROR+BIRTHDAY_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return BIRTHDAY_PAGE;
    }
}
