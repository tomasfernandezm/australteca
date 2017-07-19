package org.australteca.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tomi on 19/07/17.
 */
public class HTMLParser{

    public String parse(String filePath){
        String s = readFile(filePath);
        s = cleanHTMLString(s);
        s = removeTags(s);
        return s;
    }

    public String setParameters(String s, String p1, String p2, String p3, String p4){
        s = s.replace("________", p1);
        s = s.replace("________", p2);
        s = s.replace("________", p3);
        s = s.replace("________", p4);
        return s;
    }

    public String cleanHTMLString(String htmlString){
        htmlString = htmlString.replaceAll("\n", "");
        return htmlString;
    }

    public String removeTags(String htmlString){
        htmlString = htmlString.replaceAll("<body>", "");
        htmlString = htmlString.replaceAll("</body>", "");
        htmlString = htmlString.replaceAll("<head>", "");
        htmlString = htmlString.replaceAll("</head>", "");
        htmlString = htmlString.replaceAll("<html>", "");
        htmlString = htmlString.replaceAll("</html>", "");
        htmlString = htmlString.replaceAll("<!doctype html>", "");
        return htmlString;
    }

    public String readFile(String fileName){
        String content = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                content +=str;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
