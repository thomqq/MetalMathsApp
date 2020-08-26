package tq.arxsoft.metalmaths.operation;

import java.util.List;

public interface Exercise {
    String getQeustion();
    List<String> getAnswerWithOnlyOneCorrect();
    boolean checkAnswer(String answer);
    ExerciseType getType();
}
