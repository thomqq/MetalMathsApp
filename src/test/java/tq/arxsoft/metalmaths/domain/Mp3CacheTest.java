package tq.arxsoft.metalmaths.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Mp3CacheTest {

    //final static private String PATH_DO_THE_CACHE = "C:\\Temp\\test_mp3Cache\\";

    @TempDir
    File tempDir;

    @Test
    void ShouldReturnInfoIfResourceExists() throws IOException {
        String text = "cosik";
        String md5_text = "B3DD004ACDB3AE9F033DDAF0E5B96AED";
        Path path = Paths.get(tempDir.getPath() + "\\" + md5_text);

        Files.createFile(path);

        //when
        Mp3Cache cache = new Mp3Cache(tempDir.getPath().toString(), new IdentityUtil());
        //given
        //put formula to empty cache
        cache.getPathFor(text);
        //then
        boolean result = cache.contain(text);
        assertEquals(true, result);

        Files.delete(path);

    }

}