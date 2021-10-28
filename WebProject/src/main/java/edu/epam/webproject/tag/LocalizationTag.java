package edu.epam.webproject.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LocalizationTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public int doStartTag() throws JspException {
        try{
            JspWriter out = pageContext.getOut();
            out.write("<div>" +
                    "<input type=\"hidden\" name=\"command\" value=\"change_locale_command\"/>" +
                    "<button type=\"submit\" name=\"locale\" value=\"en\">" +
                    "EN" +
                    "</button>" +
                    "|" +
                    "<button type=\"submit\" name=\"locale\" value=\"ru\">" +
                    "RU" +
                    "</button>" +
                    "</div>");
        } catch (IOException e) {
            logger.error("Unable to do startTag", e);
        }
        return SKIP_BODY;
    }
}
