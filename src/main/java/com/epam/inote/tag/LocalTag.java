package com.epam.inote.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created by User on 13.08.2017.
 */
public class LocalTag extends BodyTagSupport {
    private String local;
    private String uriImg;
    private String value;

    public void setLocal(String loc){local = loc;}

    public void setUriImg(String uri){uriImg = uri;}

    public void setValue(String val){value = val;}

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<form action=\"inote\" method=\"post\">");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            pageContext.getOut().write("<input type=\"hidden\" name=\"command\" value=\"language\"/>");
            pageContext.getOut().write("<input type=\"hidden\" name=\"local\" value=\"" +local+"\"/>");
            pageContext.getOut().write(
                    "<input style=\"background-image: url('" +uriImg+"')\" class=\"language\" type=\"submit\" value=\""+value+"\"/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</form>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
