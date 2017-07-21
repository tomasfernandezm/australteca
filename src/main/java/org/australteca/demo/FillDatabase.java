package org.australteca.demo;

import org.australteca.demo.generator.*;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;

/**
 * Created by tomi on 27/06/17.
 */
public class FillDatabase {

    public static void main(String[] args) {
        System.out.println("Generating Users...");
        new UserGenerator().generate();
        System.out.println("All users generated!");
        System.out.println("Generating Subject...");
        new SubjectGenerator().generate();
        System.out.println("All subjects generated!");
        System.out.println("Generating comments");
        new CommentaryGenerator().generate();
        System.out.println("All comments generated!");
        System.out.println("Generating professors...");
        new ProfessorGenerator().generate();
        System.out.println("All professors generated!");
        System.out.println("Generating publications");
        new PublicationGenerator().generate();
        System.out.println("All publications generated!");
        System.out.println("Generating relationships");
        new RelationshipGenerator(50).generate();
        System.out.println("All relationships generated!");
        return;
    }
}
