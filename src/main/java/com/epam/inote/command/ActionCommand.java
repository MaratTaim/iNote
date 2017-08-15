package com.epam.inote.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 25.07.2017.
 *
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
