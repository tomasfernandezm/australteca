package org.australteca.entity;

import javax.persistence.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tomi on 19/04/17.
 */

@Entity
public class Note implements EntityInterface{

    @Id @GeneratedValue
    private int id;
    private String name;
    private String type;
    private Date date;
    private float score;

    @Lob
    private byte[] file;
    private int downloads;

    @ManyToOne
    private User author;

    public Note(){

    }

    public Note(String name, String type, byte[] file, User author) {
        this.name = name;
        this.type = type;
        this.file = file;
        this.author = author;
        date = new Date();
        score = 0;
        downloads = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
