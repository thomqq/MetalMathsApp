package tq.arxsoft.metalmaths.operation;

import java.util.List;

public interface Exercise {
    String getQuestion();
    List<String> getAnswerWithOnlyOneCorrect();
    boolean checkAnswer(String answer);
    ExerciseType getType();
    default String getLanguage() {
        return "EN";
    }
}
