package tq.arxsoft.metalmaths.exercise.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AudioController {

    private ServletContext servletContext;

    @Autowired
    public AudioController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("/mp3Formula")
    public void getMp3AsByttesArrays(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        File file = new ClassPathResource("first2.mp3").getFile();
        InputStream is = new FileInputStream(file);
        IOUtils.copy(is, response.getOutputStream());
    }
}
