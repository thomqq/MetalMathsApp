package tq.arxsoft.metalmaths.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void ShouldExtractLanguage() {
        //given
        String text = "{EN}Some text";
        //when
        String[] result = StringUtil.extractLanguage(text);
        //then
        assertArrayEquals(new String[] {"EN", "Some text"}, result);
    }

    @Test
    void ShouldExtractLanguageWithoutLang() {
        //given
        String text = "Some text";
        //when
        String[] result = StringUtil.extractLanguage(text);
        //then
        assertArrayEquals(new String[] {"", "Some text"}, result);
    }

}