package com.epam.inote.command;


import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.ContactDAO;
import com.epam.inote.dao.GroupDAO;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.ContactType;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

/**
 * Created by User on 25.07.2017.
 * view User in the view.jsp
 */
public class ViewCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(VIEW_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(connection);
            ContactDAO contactDAO = new ContactDAO(connection);
            GroupDAO groupDAO = new GroupDAO(connection);

            String id = request.getParameter(ID);

            User user = userDAO.select(id);
            user = contactDAO.select(user);
            user = groupDAO.select(user);

            pool.freeConnection(connection);

            request.setAttribute(CONT_TYPES, ContactType.values());
            request.setAttribute(USER, user);
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+VIEW_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return VIEW_PAGE;
    }
}
