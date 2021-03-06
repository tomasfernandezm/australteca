package org.australteca.servlet.filetransfer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.australteca.Constants;
import org.australteca.dao.NoteDao;
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
import javax.transaction.Transactional;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 19/04/17.
 */
public class NoteUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        byte[] data = null;
        String name = null;
        String subjectName = null;
        String type = null;
        String format = null;



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

                    /**
                     * should be relegated to an object
                     */
                    if (item.getFieldName().equals(NOTE_NAME_PARAM)) {
                        name = item.getString();
                        //  name = checkExtension(name, format);
                    } else if (item.getFieldName().equals(NOTE_TYPE_PARAM)) type = item.getString();
                    else if (item.getFieldName().equals(SUBJECT_NAME_PARAM)) subjectName = item.getString();
                } else {
                    /**
                     * should be relegated to same object as above
                     */
                    format = item.getContentType();
                    if(format != null) format = "."+item.getName().substring(item.getName().lastIndexOf(".")+1);
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


        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        Note note = new Note(name, type, data, user, format, subject);
        NoteDao noteDao = new NoteDao();
        Integer id = noteDao.add(note);

        Note noteWithID = noteDao.get(id);

        subject.getNoteList().add(noteWithID);
        user.noteUploaded();

        userDao.merge(user);
        subjectDao.merge(subject);

        resp.sendRedirect("/servlet/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
