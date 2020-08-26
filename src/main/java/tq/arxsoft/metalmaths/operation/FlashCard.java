package tq.arxsoft.metalmaths.operation;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FlashCard implements Exercise{

    private String question;
    private String answer;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String getQeustion() {
        return question;
    }

    @Override
    public List<String> getAnswerWithOnlyOneCorrect() {
        return Arrays.asList(answer);
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.toUpperCase().equals(userAnswer.toUpperCase());
    }

    @Override
    public ExerciseType getType() {
        return ExerciseType.CardSpelling;
    }
}
