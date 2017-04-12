package org.australteca.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "COMMENTARY")
public class Commentary {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "COMMENTARY_ID")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column (name = "COMMENTARY_CREATION_DATE")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column (name = "COMMENTARY_LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column (name = "COMMENTARY_COMMENTARY")
    private String commentary;

    @OneToOne
    private User author;

    @OneToOne
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

    public int getId() {
        return id;
    }
}
