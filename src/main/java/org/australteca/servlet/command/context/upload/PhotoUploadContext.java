package org.australteca.servlet.command.context.upload;

import org.apache.commons.fileupload.FileItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.australteca.Constants.NOTE_NAME_PARAM;

/**
 * Created by tomi on 28/04/17.
 */
public class PhotoUploadContext implements FileUploadContext {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private String photoName;
    private String photoFormat;

    @Override
    public void setFormField(FileItem item) {
        if (item.getFieldName().equals(NOTE_NAME_PARAM)) photoName = item.getString();
    }

    @Override
    public void setFileExtension(FileItem item) {
        photoFormat = item.getContentType();
        photoFormat = "."+photoFormat.substring(photoFormat.lastIndexOf("/")+1);
    }

    @Override
    public void writeBytes(byte[] data) throws IOException {
        outputStream.write(data);
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public String getPhotoName() {
        return photoName;
    }

    public String getPhotoFormat() {
        return photoFormat;
    }
}
