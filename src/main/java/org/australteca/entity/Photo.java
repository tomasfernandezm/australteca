package org.australteca.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by tomi on 29/04/17.
 */
@Embeddable
public class Photo {

    @Lob
    @Column(length = 1024*1024*15)
    private byte[] data;

    private String format;

    private String ext;

    public Photo() {
    }

    Photo(byte[] data, String format, String ext) {
        this.data = data;
        this.format = format;
        this.ext = ext;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
