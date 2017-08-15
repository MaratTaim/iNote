package com.epam.inote.command;


import com.epam.inote.constant.Const;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.07.2017.
 *
 */
public class EmptyCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(EMPTY_COMMAND);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        LOGGER.info(EX_END);
        return ERROR_PAGE;
    }
}