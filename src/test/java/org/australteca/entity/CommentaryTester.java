package org.australteca.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * Created by tomi on 12/07/17.
 */
public class CommentaryTester {

    @Test
    public void testOrder(){
        Subject subject = new Subject("aaaaa");
        Commentary commentary1 = new Commentary("comentary1", null, subject);
        Commentary commentary2 = new Commentary("comentary2", null, subject);
        Commentary commentary3 = new Commentary("comentary3", null, subject);

        subject.getCommentaryList().add(0, commentary1);
        subject.getCommentaryList().add(0, commentary2);
        subject.getCommentaryList().add(0, commentary3);

        for(int i = 0;i <= 2;i++){
            System.out.println(subject.getCommentaryList().get(i).getCommentary() + " " + subject.getCommentaryList().get(i).getFormatDate2());
        }
    }
}
