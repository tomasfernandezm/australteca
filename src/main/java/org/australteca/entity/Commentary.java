package org.australteca.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "COMMENTARY")
public class Commentary extends AbstractEntity implements Comparable<Commentary>{

    @Temporal(TemporalType.DATE)
    @Column (name = "COMMENTARY_CREATION_DATE")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column (name = "COMMENTARY_LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column (name = "COMMENTARY_COMMENTARY")
    private String commentary;

    @ManyToOne
    private User author;

    @ManyToOne
    private Subject subject;

    public Commentary() {
    }

    public Commentary(String commentary, User author, Subject subject) {
        this.creationDate = new Date();
        this.lastModificationDate = creationDate;
        this.commentary = commentary;
        this.author = author;
        this.subject = subject;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getFormatDate2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(creationDate);
    }

    public Subject getSubject(){
        return subject;
    }

    @Override
    public int compareTo(Commentary o) {
        return creationDate.compareTo(o.getCreationDate());
    }
}
