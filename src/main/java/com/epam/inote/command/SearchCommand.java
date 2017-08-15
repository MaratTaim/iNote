package com.epam.inote.command;


import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.ContactDAO;
import com.epam.inote.dao.GroupDAO;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by User on 25.07.2017.
 * Searching command with LIKE %...%
 */
public class SearchCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(SEARCH_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            Map<Integer, User> map;
            String search = request.getParameter(SEARCH);

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(connection);
            ContactDAO contactDAO = new ContactDAO(connection);
            GroupDAO groupDAO = new GroupDAO(connection);

            map = userDAO.getSearch(search);
            map = contactDAO.addContactToUser(map);
            map = groupDAO.addGroupToUser(map);

            pool.freeConnection(connection);

            request.setAttribute(LIST, map.values());
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+SEARCH_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return MAIN_PAGE;
    }
}
