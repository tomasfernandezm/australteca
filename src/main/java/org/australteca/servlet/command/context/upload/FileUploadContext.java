package org.australteca.servlet.command.context.upload;

import org.apache.commons.fileupload.FileItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tomi on 28/04/17.
 */
public interface FileUploadContext {

    void setFormField(FileItem item);
    void setFileExtension(FileItem item);
    void writeBytes(byte[] data) throws IOException;
    ByteArrayOutputStream getOutputStream();
}
