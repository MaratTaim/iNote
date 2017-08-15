package com.epam.inote.command;

import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.ContactDAO;
import com.epam.inote.dao.GroupDAO;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import com.epam.inote.resource.LanguageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by User on 04.08.2017.
 * Переделать в teglib
 */
public class LanguageCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(LANGUAGE_COMMAND);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        try {
            String local = request.getParameter(LOCAL);
            HttpSession session = request.getSession();
            LanguageManager languageManager = (LanguageManager) session.getAttribute(LOCAL);
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            if (languageManager != null) {
                switch (local) {
                    case RU:
                        languageManager.changeRU();
                        break;
                    case US:
                        languageManager.changeUS();
                        break;
                }
            }
            Map<Integer, User> map;
            map = new UserDAO(connection).getAllSorted();
            map = new ContactDAO(connection).addContactToUser(map);
            map = new GroupDAO(connection).addGroupToUser(map);

            pool.freeConnection(connection);

            request.setAttribute(LIST, map.values());
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+LANGUAGE_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return MAIN_PAGE;
    }
}
