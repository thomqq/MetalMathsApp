package tq.arxsoft.metalmaths.exercise;

import lombok.Data;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import tq.arxsoft.metalmaths.operation.Exercise;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class ExerciseContext {
    private Exercise currentExercise;
}
