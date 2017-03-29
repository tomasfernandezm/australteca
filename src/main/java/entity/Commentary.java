package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "COMMENTARY")
public class Commentary {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @Column (name = "CREATIONDATE")
    private Date creationDate;

    @Column (name = "LASTMODIFICATIONDATE")
    private Date lastModificationDate;

    @Column (name = "COMMENTARY")
    private String commentary;

    @OneToOne
    private User author;

    public Commentary() {
    }

    public Commentary(Date creationDate, String commentary, User author) {
        this.creationDate = creationDate;
        this.lastModificationDate = creationDate;
        this.commentary = commentary;
        this.author = author;
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
}
