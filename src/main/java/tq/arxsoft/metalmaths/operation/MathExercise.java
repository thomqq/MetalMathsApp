package tq.arxsoft.metalmaths.operation;

import lombok.Data;

@Data
abstract public class MathExercise implements Exercise {
    protected int first;
    protected int second;
    protected ExerciseType exerciseType = ExerciseType.MathInput;

    public MathExercise(int first, int second) {
        init(first, second);
    }

    public abstract void init(int first, int second);
}
