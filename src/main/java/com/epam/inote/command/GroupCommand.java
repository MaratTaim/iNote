package com.epam.inote.command;

import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.GroupDAO;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

/**
 * Created by User on 25.07.2017.
 * finding By Groups, You choice contacts by his group
 */
public class GroupCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(GROUP_COMMAND);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            String gname = request.getParameter(GNAME);
            Collection<User> collection = null;
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            GroupDAO groupDAO = new GroupDAO(connection);
            UserDAO userDAO = new UserDAO(connection);

            if (gname != null) {
                ResultSet resultSet = groupDAO.searchGroup(gname);
                collection = userDAO.findAll(resultSet).values();
            }

            List<String> listGroup = groupDAO.selectAll();

            pool.freeConnection(connection);

            request.setAttribute(LIST, collection);
            request.setAttribute(GROUPS, listGroup);
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+GROUP_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return GROUP_PAGE;
    }
}
