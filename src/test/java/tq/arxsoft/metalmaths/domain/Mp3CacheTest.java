package tq.arxsoft.metalmaths.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class Mp3CacheTest {

    final static private File tempDir = new File("C:\\Temp\\test_mp3Cache\\");

    @Test
    void ShouldReturnInfoIfResourceExists() throws IOException {
        String text = "cosik";
        String md5_text = "B3DD004ACDB3AE9F033DDAF0E5B96AED";
        Path path = Paths.get(tempDir.getPath() + "\\" + md5_text);

        Files.createFile(path);

        //when
        Mp3Cache cache = new Mp3Cache(tempDir.getPath().toString(), new IdentityUtil());
        //given
        //then
        boolean result = cache.contain(text, "EN");
        assertEquals(true, result);

        Files.delete(path);

    }

    @Test
    void ShouldSaveFileWithMp3() throws IOException {
        //when
        String text = "cosik";
        String md5_text = "B3DD004ACDB3AE9F033DDAF0E5B96AED";
        Path path = Paths.get(tempDir.getPath() + "\\" + text + ".txt");
        Files.write(path, text.getBytes());

        Mp3Cache cache = new Mp3Cache(tempDir.getPath().toString(), new IdentityUtil());

        //given
        cache.save(text, Files.newInputStream(path), "EN");

        //then
        boolean result = cache.contain(text, "EN");
        assertEquals(true, result);
    }


}