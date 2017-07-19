package org.australteca.email;

import org.junit.Test;

/**
 * Created by tomi on 19/07/17.
 */
public class HTMLParserTester {

    @Test
    public void htmlCleanupTest(){
        String html = "<something with quotes=\"asdasdasdasd\">";
        HTMLParser htmlParser = new HTMLParser();
        System.out.println(htmlParser.cleanHTMLString(html));
    }
}
