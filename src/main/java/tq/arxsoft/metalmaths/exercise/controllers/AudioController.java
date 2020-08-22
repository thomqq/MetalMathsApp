package tq.arxsoft.metalmaths.exercise.controllers;

import com.amazonaws.services.polly.model.OutputFormat;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tq.arxsoft.metalmaths.services.PollyService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AudioController {

    private ServletContext servletContext;
    private PollyService pollyService;

    @Autowired
    public AudioController(ServletContext servletContext, PollyService pollyService) {
        this.servletContext = servletContext;
        this.pollyService = pollyService;
    }

    @RequestMapping("/mp3Formula")
    public void getMp3AsByttesArrays(HttpServletResponse response, @RequestParam String formula) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        InputStream is = pollyService.synthesize(formula, OutputFormat.Mp3);
        IOUtils.copy(is, response.getOutputStream());
    }
}
