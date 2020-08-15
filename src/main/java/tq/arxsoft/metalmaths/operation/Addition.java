package tq.arxsoft.metalmaths.operation;


import lombok.Data;

import java.util.*;

@Data
public class Addition implements Exercise {

    private int first;
    private int second;

    private int maxResult;

    public Addition(int first, int second) {
        this.first = first;
        this.second = second;

        maxResult = 3 * (second + first);
    }

    @Override
    public String getFormula() {
        StringBuilder builder = new StringBuilder();
        builder.append(first);
        builder.append(" + ");
        builder.append(second);
        return builder.toString();
    }

    @Override
    public List<Integer> getAnswerWithOnlyOneCorrect() {
        Set<Integer> answers = new TreeSet<>();
        answers.add( first + second );
        Random rnd = new Random(System.currentTimeMillis());
        while (answers.size() < 3) {
            int number = rnd.nextInt(maxResult);
            answers.add(number);
        }
        return new ArrayList<Integer>(answers);
    }

    @Override
    public boolean checkAnswer(int answer) {
        return answer == first + second;
    }
}
