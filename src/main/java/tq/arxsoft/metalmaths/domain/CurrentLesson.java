package tq.arxsoft.metalmaths.domain;

import tq.arxsoft.metalmaths.operation.Exercise;

public interface CurrentLesson {
    boolean isNextExercise();
    Exercise getNextExercise();
    Exercise getCurrentExercise();
    boolean checkAnswer(String answer);
    long getLessonId();
}
