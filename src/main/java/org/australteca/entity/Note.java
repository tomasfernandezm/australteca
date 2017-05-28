package org.australteca.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by tomi on 19/04/17.
 */

@Entity
@Table(name = "NOTE")
public class Note extends AbstractEntity{

    @Column(name = "NOTE_NAME")
    private String name;

    @Column(name = "NOTE_TYPE")
    private String type;

    private String format;

    @Temporal(TemporalType.DATE)
    @Column(name = "NOTE_DATE")
    private Date date;

    @Column(name = "NOTE_SCORE")
    private float score;

    @Lob
    @Column(name = "NOTE_DATA", length = 1024*1024*5)
    private byte[] data;

    @Column(name = "NOTE_NUMBER_OF_DOWNLOADS")
    private int downloads;

    @ManyToOne
    private User author;

    @ManyToOne
    private Subject subject;

    public Note(){

    }

    public Note(String name, String type, byte[] data, User author, String format, Subject subject) {
        this.format = format;
        this.name = name;
        this.type = type;
        this.data = data;
        this.author = author;
        this.subject = subject;
        date = new Date();
        score = 0;
        downloads = 0;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public void increaseDownloads(){
        downloads++;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFormatDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
