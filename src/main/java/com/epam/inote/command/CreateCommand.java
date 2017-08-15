package com.epam.inote.command;

import com.epam.inote.constant.Const;
import com.epam.inote.model.ContactType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.07.2017.
 * adding new User in the addnew.jsp
 */
public class CreateCommand extends Const implements ActionCommand {
    private final Logger LOGGER = Logger.getLogger(CREATE_COMMAND);
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.info(EX_START);
        request.setAttribute(CONT_TYPES, ContactType.values());
        LOGGER.info(EX_END);
        return ADD_PAGE;
    }
}
