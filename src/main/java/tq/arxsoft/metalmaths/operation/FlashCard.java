package tq.arxsoft.metalmaths.operation;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FlashCard implements Exercise{

    private String question;
    private String answer;
    private String lang;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        lang = "EN";
    }

    public FlashCard(String question, String answer, String lang) {
        this.question = question;
        this.answer = answer;
        this.lang = lang;
    }

    @Override
    public String getQuestion() {
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

    @Override
    public String getLanguage() {
        return lang;
    }
}
