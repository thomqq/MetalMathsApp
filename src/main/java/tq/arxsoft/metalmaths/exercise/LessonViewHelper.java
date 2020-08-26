package tq.arxsoft.metalmaths.exercise;

import lombok.Data;
import tq.arxsoft.metalmaths.operation.ExerciseType;


public class LessonViewHelper {
    public String getViewForExercise(ExerciseType type) {
        String result = "";
        switch (type) {
            case MathInput:
                result="math_input";
                break;
            default:
                result = "math";
        }

        return result;
    }
}
