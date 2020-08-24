package tq.arxsoft.metalmaths.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IdentityUtilTest {

    @ParameterizedTest
    @CsvSource({"cosik,B3DD004ACDB3AE9F033DDAF0E5B96AED"})
    void ShouldGenerateIdentytySameForTheSameText(String formula, String md5) {
        //when
        IdentityUtil identityUtil = new IdentityUtil();
        //given
        //then
        for( int i = 0; i < 5 ; ++i) {
            assertEquals(md5, identityUtil.generateIdentity(formula));
        }
    }

    @ParameterizedTest
    @CsvSource({"bleble,7BBFDCF7C08DE638F06F0D85A0B95FC9", "robot,87B7CB79481F317BDE90C116CF36084B", "net&net,6214674B0749515D130BB3701D8C3A3C"})
    void ShouldGenerateIdentytyDiffernetForTheDifferentText(String formula, String md5) {
        //when
        IdentityUtil identityUtil = new IdentityUtil();
        //given
        //then
        assertEquals(md5, identityUtil.generateIdentity(formula));
    }

}