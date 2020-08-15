package tq.arxsoft.metalmaths.operation;

import java.util.List;

public interface Exercise {
    String getFormula();
    List<Integer> getAnswerWithOnlyOneCorrect();
    boolean checkAnswer(int parseInt);
}
