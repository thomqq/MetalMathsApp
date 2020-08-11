package tq.arxsoft.metalmaths.operation;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Addition implements ExerciseInterface {

    private int first;
    private int second;

    public Addition(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String getFormula() {
        StringBuilder builder = new StringBuilder();
        builder.append(first);
        builder.append(" + ");
        builder.append(second);
        return builder.toString();
    }
}
