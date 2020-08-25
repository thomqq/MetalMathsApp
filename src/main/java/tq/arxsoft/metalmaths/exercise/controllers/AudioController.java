package tq.arxsoft.metalmaths.exercise.controllers;

import com.amazonaws.services.polly.model.OutputFormat;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tq.arxsoft.metalmaths.domain.Mp3Cache;
import tq.arxsoft.metalmaths.services.PollyService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AudioController {

    private ServletContext servletContext;
    private PollyService pollyService;
    private Mp3Cache mp3Cache;

    @Autowired
    public AudioController(ServletContext servletContext, PollyService pollyService, Mp3Cache mp3Cache) {
        this.servletContext = servletContext;
        this.pollyService = pollyService;
        this.mp3Cache = mp3Cache;
    }

    @RequestMapping("/mp3Formula")
    public void getMp3AsByttesArrays(HttpServletResponse response, @RequestParam String formula) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        if( !mp3Cache.contain(formula) ) {
            mp3Cache.save(formula, pollyService.synthesize(formula, OutputFormat.Mp3));
        }
        String pathForMp3File = mp3Cache.getPathFor(formula);
        if( pathForMp3File == null) {
            throw new RuntimeException("TQ: some problem with: " + formula);
        }
        InputStream is = new FileInputStream(pathForMp3File);
        IOUtils.copy(is, response.getOutputStream());
    }
}
