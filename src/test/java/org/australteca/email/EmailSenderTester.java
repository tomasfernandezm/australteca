package org.australteca.email;

import org.junit.Test;

/**
 * Created by tomi on 12/06/17.
 */
public class EmailSenderTester {

    @Test
    public void sendPlainTextTest(){
        EmailSender emailSender = new EmailSender();
        emailSender.send("tomas.forman@ing.austral.edu.ar", "LAB", "LAB", false);
    }

    @Test
    public void sendHTMLTextTest(){
        String filePath = "/home/tomi/projects/australteca/src/main/webapp/html/EmailTemplate.html";
        EmailSender emailSender = new EmailSender();
        HTMLParser htmlParser = new HTMLParser();
        String parsedHTML = htmlParser.parse(filePath);
        parsedHTML = htmlParser.setParameters(parsedHTML, "Tomás Fernández", "Mark Zuckerberg", "asdfasdfasdfasd", "fracasado");
        emailSender.send("tomas.martinez@ing.austral.edu.ar", "LAB", parsedHTML, true);
    }
}
