package com.epam.inote.command;


import com.epam.inote.constant.Const;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.07.2017.
 */
public class LogoutCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(LOGOUT_COMMAND);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        request.getSession().invalidate();
        LOGGER.info(EX_END);
        return LOGIN_PAGE;
    }
}