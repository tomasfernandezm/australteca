package org.australteca.email;

import org.junit.Test;

/**
 * Created by tomi on 12/06/17.
 */
public class EmailSenderTester {

    @Test
    public void testSend(){
        EmailSender emailSender = new EmailSender();
        emailSender.send("tomas.forman@ing.austral.edu.ar", "LAB", "LAB");
    }
}
