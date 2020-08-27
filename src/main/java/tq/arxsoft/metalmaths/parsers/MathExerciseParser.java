package tq.arxsoft.metalmaths.parsers;

import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.MathExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MathExerciseParser implements ExerciseParser {

    @Override
    public List<? extends Exercise> parse(Map<String, List<String>> items, ExerciseType exerciseType) {
        String[] operationItem = items.get("operation").get(0).split(" ");
        List<String> values = new ArrayList<>();
        List<MathExercise> mathExercises = new ArrayList<>();
        MathExercise mathExercise = null;
        for( String item : operationItem) {
            if (!item.isEmpty() && !item.equals(" ")) {
                if (item.equals("+")) {
                    mathExercise = new Addition(exerciseType);
                    continue;
                }
                if( items.containsKey(item.toLowerCase())) {
                    values.add(items.get(item.toLowerCase()).get(0));
                }
            }
        }
        mathExercise.init(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)));
        mathExercises.add(mathExercise);
        return mathExercises;
    }
}
