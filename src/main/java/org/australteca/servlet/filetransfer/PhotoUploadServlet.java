package org.australteca.servlet.filetransfer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Note;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.australteca.Constants.NOTE_NAME_PARAM;
import static org.australteca.Constants.NOTE_TYPE_PARAM;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 29/04/17.
 */
public class PhotoUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        byte[] data = null;
        String contentType = null;
        String extension = null;

        try{
            // Check that we have a file upload request
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(req);

            // Parse the request
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            for(FileItem item: items) {
                if (item.isFormField()) {

                } else {
                    /**
                     * should be relegated to same object as above
                     */
                    contentType = item.getContentType();
                    if(contentType != null) extension = "."+contentType.substring(contentType.lastIndexOf("/")+1);
                    outputStream.write(item.get());
                }
            }
            data = outputStream.toByteArray();

        }catch (FileUploadException e){
            e.printStackTrace();
        }

        /**
         * should be relegated to separate object (command maybe)
         */

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        user.setPhoto(data, contentType, extension);
        userDao.merge(user);

        resp.sendRedirect("/servlet/userPost");
    }
}
