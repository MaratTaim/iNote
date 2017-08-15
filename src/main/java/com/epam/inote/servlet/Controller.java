package com.epam.inote.servlet;

import com.epam.inote.command.ActionCommand;
import com.epam.inote.command.factory.ActionFactory;
import com.epam.inote.resource.ConfigurationManager;
import com.epam.inote.resource.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marat on 15.07.2017.
 *
 */
public class Controller extends HttpServlet {
    private final String ERROR_PAGE = "path.page.error";
    private final String WRONG_ACTION = "wrongAction";
    private final String MESSAGE = "message.nullpage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        String page;

        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(ERROR_PAGE);
            request.getSession().setAttribute(WRONG_ACTION, MessageManager.getProperty(MESSAGE));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}