package org.australteca.servlet.context.upload;

import org.apache.commons.fileupload.FileItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tomi on 28/04/17.
 */
public interface FileUploadContext {

    void setFormField(FileItem item);
    void setFileExtension(FileItem item);
    void writeBytes(byte[] data) throws IOException;
    ByteArrayOutputStream getOutputStream();
}
