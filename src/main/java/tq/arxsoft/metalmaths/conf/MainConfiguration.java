package tq.arxsoft.metalmaths.conf;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tq.arxsoft.metalmaths.domain.IdentityUtil;
import tq.arxsoft.metalmaths.domain.Mp3Cache;
import tq.arxsoft.metalmaths.exercise.LessonViewHelper;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.parsers.ExerciseParser;
import tq.arxsoft.metalmaths.parsers.FlashParser;
import tq.arxsoft.metalmaths.parsers.MathExerciseParser;
import tq.arxsoft.metalmaths.parsers.SpellingParser;
import tq.arxsoft.metalmaths.services.PollyLang;
import tq.arxsoft.metalmaths.services.PollyLangUtil;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public Map<ExerciseType, ExerciseParser> getParsers() {
        Map<ExerciseType, ExerciseParser> parsers = new HashMap<>();
        parsers.put(ExerciseType.MathAnswer, new MathExerciseParser());
        parsers.put(ExerciseType.MathInput, new MathExerciseParser());
        parsers.put(ExerciseType.CardFlash, new FlashParser());
        parsers.put(ExerciseType.CardSpelling, new SpellingParser());

        return parsers;
    }

    @Bean
    public PollyLang getPollyLang() {
        return new PollyLangUtil();
    }
}
