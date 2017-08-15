package com.epam.inote.command.factory;

import com.epam.inote.command.ActionCommand;
import com.epam.inote.command.EmptyCommand;
import com.epam.inote.command.type.CommandEnum;
import com.epam.inote.resource.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.07.2017.
 *
 */
public class ActionFactory {
    private final Logger LOGGER = Logger.getLogger("ActionFactory");
    private final String COMMAND = "command";
    private final String WRONG_ACTION = "wrongAction";
    private final String MESSAGE = MessageManager.getProperty("message.wrongaction");


    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(COMMAND);

        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.warn(WRONG_ACTION+" "+action);
            request.setAttribute(WRONG_ACTION, action + MESSAGE);
        }
        return current;
    }
}
