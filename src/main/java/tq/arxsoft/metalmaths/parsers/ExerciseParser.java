package tq.arxsoft.metalmaths.parsers;

import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;

import java.util.List;
import java.util.Map;

public interface ExerciseParser {
    List<? extends Exercise> parse(Map<String, List<String>> items, ExerciseType exerciseType);
}
