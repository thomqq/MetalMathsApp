package tq.arxsoft.metalmaths.conf;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tq.arxsoft.metalmaths.domain.IdentityUtil;
import tq.arxsoft.metalmaths.domain.Mp3Cache;
import tq.arxsoft.metalmaths.exercise.LessonViewHelper;

@Configuration
public class MainConfiguration {

    @Bean
    Region getRegion() {
        return Region.getRegion(Regions.US_EAST_1);
    }

    @Bean
    Mp3Cache getMp3Cache(@Value("${mp3.cache.path}") String mp3CachePath) {
        return new Mp3Cache(mp3CachePath, getIndentityUtil());
    }

    @Bean
    IdentityUtil getIndentityUtil() {
        return new IdentityUtil();
    }

    @Bean
    LessonViewHelper getLessonViewHelper() {
        return new LessonViewHelper();
    }
}
