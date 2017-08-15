package com.epam.inote.filter;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    private String encoding;
    private final String UTF = "utf-8";
    private final String ENCOD = "requestEncoding";

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(ENCOD);
        if (encoding == null)
            encoding = UTF;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
