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
import java.sql.Date;
import java.util.Map;

/**
 * Created by User on 25.07.2017.
 * update user (POST) and return to the main page
 */
public class UpdateCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(UPDATE_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            Map<Integer, User> map;
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            UserDAO userDAO = new UserDAO(connection);
            ContactDAO contactDAO = new ContactDAO(connection);
            GroupDAO groupDAO = new GroupDAO(connection);

            String id = request.getParameter(ID);
            String surname = request.getParameter(SURNAME);
            String name = request.getParameter(NAME);
            String address = request.getParameter(ADDRESS);
            String groups = request.getParameter(GROUPS);
            String[] splitGroups = groups.split(COMMA);
            Date sd = Date.valueOf(request.getParameter(BIRTHDAY));

            User user = new User(Integer.parseInt(id), surname, name, address, sd.toLocalDate());

            for (String str : splitGroups) {
                if (str != null) user.setGroup(str.trim());
            }

            for (ContactType type : ContactType.values()) {
                String value = request.getParameter(type.name());
                if (value == null || value.isEmpty()) {
                    continue;
                }
                user.setContact(type, value);
            }

            userDAO.update(user);
            contactDAO.update(user);
            groupDAO.update(user);

            map = userDAO.getAllSorted();
            map = contactDAO.addContactToUser(map);
            map = groupDAO.addGroupToUser(map);

            pool.freeConnection(connection);

            request.setAttribute(LIST, map.values());
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+UPDATE_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return MAIN_PAGE;
    }
}
