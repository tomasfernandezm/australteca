package org.australteca.servlet.context.upload;

import org.apache.commons.fileupload.FileItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 28/04/17.
 */
public class NoteUploadContext implements FileUploadContext {

    private String noteName;
    private String noteType;
    private String noteFormat;
    private String subjectName;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    public void setFormField(FileItem item) {
        if (item.getFieldName().equals(NOTE_NAME_PARAM)) noteName = item.getString();
        else if (item.getFieldName().equals(NOTE_TYPE_PARAM)) noteType = item.getString();
        else if (item.getFieldName().equals(SUBJECT_NAME_PARAM)) subjectName = item.getString();
    }

    @Override
    public void setFileExtension(FileItem item) {
        noteFormat = item.getContentType();
        noteFormat = "."+noteFormat.substring(noteFormat.lastIndexOf("/")+1);
    }

    @Override
    public void writeBytes(byte[] data) throws IOException{
        outputStream.write(data);
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteType() {
        return noteType;
    }

    public String getNoteFormat() {
        return noteFormat;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
