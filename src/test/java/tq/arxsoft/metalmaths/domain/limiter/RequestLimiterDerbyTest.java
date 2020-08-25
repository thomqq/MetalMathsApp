package tq.arxsoft.metalmaths.domain.limiter;

import lombok.Lombok;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RequestLimiterDerbyTest {

    @Autowired
    RequestLimiterDerby requestLimiterDerby;

    @Test
    void isLimitReached() {
        //when
        requestLimiterDerby.setMaxAllowedRequest(2);
        requestLimiterDerby.setDate(LocalDate.now().minusDays(1));
        //given
        //then
        assertEquals(false, requestLimiterDerby.isLimitReached());
        assertEquals(false, requestLimiterDerby.isLimitReached());
        assertEquals(true, requestLimiterDerby.isLimitReached());
    }
}