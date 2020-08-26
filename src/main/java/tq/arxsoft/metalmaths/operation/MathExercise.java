package tq.arxsoft.metalmaths.operation;

import lombok.Data;

import java.util.Random;

@Data
abstract public class MathExercise implements Exercise {

    protected RandomUtil randomUtil = new RandomUtil() {
        Random random = new Random(System.currentTimeMillis());
        @Override
        public int nextInt(int range) {
            return random.nextInt(range + 1) ;
        }
    };

    protected int first;
    protected int second;
    protected ExerciseType exerciseType;

    public MathExercise(int first, int second, ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
        init(first, second);
    }

    public abstract void init(int first, int second);


}
