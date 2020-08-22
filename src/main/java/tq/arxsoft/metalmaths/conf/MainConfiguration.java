package tq.arxsoft.metalmaths.conf;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tq.arxsoft.metalmaths.domain.CurrentLesson;
import tq.arxsoft.metalmaths.domain.SimpleRandomLesson;

@Configuration
public class MainConfiguration {

    @Bean
    Region getRegion() {
        return Region.getRegion(Regions.US_EAST_1);
    }

}
