package org.australteca.servlet.filetransfer;

import org.australteca.Constants;
import org.australteca.dao.NoteDao;
import org.australteca.entity.Note;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.australteca.Constants.NOTE_FORMAT_PARAM;
import static org.australteca.Constants.NOTE_ID_PARAM;
import static org.australteca.Constants.NOTE_NAME_PARAM;

/**
 * Created by tomi on 23/04/17.
 */
public class DownloadServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter(NOTE_ID_PARAM));
        String name = req.getParameter(NOTE_NAME_PARAM);
        String format = req.getParameter(NOTE_FORMAT_PARAM);

        NoteDao noteDao = new NoteDao();
        Note note = noteDao.get(id);
        byte[] filedata = note.getData();

        //      set the header information in the response.
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\";");
        resp.setContentType("application/" + format);
        ByteArrayInputStream byteStream = new ByteArrayInputStream(filedata);

        // input stream con un buffer incorporado
        BufferedInputStream bufStream = new BufferedInputStream(byteStream);
        ServletOutputStream responseOutputStream = resp.getOutputStream();
        int data = bufStream.read();
        while (data != -1){
            responseOutputStream.write(data);
            data = bufStream.read();
        }

        bufStream.close();
        responseOutputStream.close();
    }
}
