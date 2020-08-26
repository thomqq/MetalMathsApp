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
    public String getQeustion() {
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
}
