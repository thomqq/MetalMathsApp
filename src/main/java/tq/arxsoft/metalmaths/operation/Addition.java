package tq.arxsoft.metalmaths.operation;


import lombok.Data;

import java.util.*;

@Data
public class Addition extends MathExercise {

    private int maxResult;

    public Addition(ExerciseType exerciseType) {
        super(0,0, exerciseType);
    }

    public Addition(int first, int second, ExerciseType exerciseType) {
        super(first, second, exerciseType);
    }

    @Override
    public void init(int first, int second) {
        this.first = randomUtil.nextInt(first);
        this.second = randomUtil.nextInt(second);
        maxResult = 3 * (second + first);
    }

    @Override
    public String getQuestion() {
        StringBuilder builder = new StringBuilder();
        builder.append(first);
        builder.append(" + ");
        builder.append(second);
        return builder.toString();
    }

    @Override
    public List<String> getAnswerWithOnlyOneCorrect() {
        Set<String> answers = new TreeSet<>();
        answers.add( Integer.toString(first + second ) );
        Random rnd = new Random(System.currentTimeMillis());
        while (answers.size() < 3) {
            int number = rnd.nextInt(maxResult);
            answers.add(Integer.toString(number));
        }
        return new ArrayList<String>(answers);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals(Integer.toString(first + second));
    }

    @Override
    public ExerciseType getType() {
        return exerciseType;
    }
}
