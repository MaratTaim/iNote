package com.epam.inote.command;


import com.epam.inote.connection.ConnectionPool;
import com.epam.inote.constant.Const;
import com.epam.inote.dao.ClientDAO;
import com.epam.inote.dao.ContactDAO;
import com.epam.inote.dao.GroupDAO;
import com.epam.inote.dao.UserDAO;
import com.epam.inote.exception.INoteException;
import com.epam.inote.model.Client;
import com.epam.inote.model.ClientType;
import com.epam.inote.model.User;
import com.epam.inote.resource.LanguageManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by User on 25.07.2017.
 */
public class LoginCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(LOGIN_COMMAND);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        String page = "";
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String email = request.getParameter(EMAIL);
            HttpSession session = null;

            // Хеширование пароля
            password = DigestUtils.md5Hex(password);

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            connection.setAutoCommit(false);

            ClientDAO clientDAO = new ClientDAO(connection);

            request.getSession().setAttribute(LOCAL, new LanguageManager());

            if (email != null) {
                if (clientDAO.add(new Client(login, password, email))) {
                    session = request.getSession();
                    session.setAttribute(CLIENT, ClientType.GUEST.name());
                } else {
                    request.setAttribute(ERROR_LPM, ERROR);
                    return REGISTER_PAGE;
                }
            }

            Client client = clientDAO.check(login, password);

            if (ADMIN.equals(login) && client.getId() != 0) {
                session = request.getSession();
                session.setAttribute(CLIENT, ClientType.ADMINISTRATOR.name());
                page = MAIN_PAGE;
            } else if (client.getId() != 0) {
                session = request.getSession();
                session.setAttribute(CLIENT, ClientType.GUEST.name());
                page = MAIN_PAGE;
            } else {
                request.setAttribute(ERROR_LPM, ERROR);
                page = LOGIN_PAGE;
            }


            Map<Integer, User> map;
            map = new UserDAO(connection).getAllSorted();
            map = new ContactDAO(connection).addContactToUser(map);
            map = new GroupDAO(connection).addGroupToUser(map);

            pool.freeConnection(connection);

            request.setAttribute(LIST, map.values());
        } catch (Exception e) {
            LOGGER.error(EX_ERROR);
            throw new INoteException(EX_ERROR+LOGIN_COMMAND,e);
        }
        LOGGER.info(EX_END);
        return page;
    }
}