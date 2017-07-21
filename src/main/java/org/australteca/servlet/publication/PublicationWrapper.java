package org.australteca.servlet.publication;

import com.github.rjeschke.txtmark.Processor;
import org.australteca.entity.Publication;

/**
 * Created by tomi on 14/07/17.
 */
public class PublicationWrapper {

    Publication publication;
    boolean favorite;
    String htmlDescription;
    String extendedProcess = "\n[$PROFILE$]: extended";

    public PublicationWrapper(Publication publication, boolean favorite) {
        this.publication = publication;
        this.favorite = favorite;
        htmlDescription = Processor.process(publication.getDescription()+extendedProcess);
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
