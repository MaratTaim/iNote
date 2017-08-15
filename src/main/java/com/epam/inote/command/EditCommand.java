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
 * Edit User in the addnew.jsp
 */
public class EditCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(EDIT_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            String id = request.getParameter(ID);
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(connection);
            ContactDAO contactDAO = new ContactDAO(connection);
            GroupDAO groupDAO = new GroupDAO(connection);

            User user = userDAO.select(id);
            user = contactDAO.select(user);
            user = groupDAO.select(user);

            pool.freeConnection(connection);

            request.setAttribute(CONT_TYPES, ContactType.values());
            request.setAttribute(USER, user);
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+EDIT_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return UPDATE_PAGE;
    }
}
