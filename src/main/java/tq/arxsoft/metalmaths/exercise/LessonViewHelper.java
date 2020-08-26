package tq.arxsoft.metalmaths.exercise;

import lombok.Data;
import tq.arxsoft.metalmaths.operation.ExerciseType;


public class LessonViewHelper {
    public String getViewForExercise(ExerciseType type) {
        String result = "";
        switch (type) {
            case CardSpelling:
            case CardFlash:
            case MathInput:
                result="math_input";
                break;
            case MathAnswer:
                result = "math";
                break;
        }

        return result;
    }
}
