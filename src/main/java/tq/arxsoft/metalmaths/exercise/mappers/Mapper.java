package tq.arxsoft.metalmaths.exercise.mappers;

import tq.arxsoft.metalmaths.model.ExerciseDTO;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.model.LessonInfoDTO;
import tq.arxsoft.metalmaths.operation.Exercise;

import java.util.stream.Collectors;

public class Mapper {
    public static ExerciseDTO mapFromAddition(Exercise addition) {
        ExerciseDTO exerciseDTO = new ExerciseDTO(addition.getQuestion(),
                addition.getAnswerWithOnlyOneCorrect().stream().map(x -> x.toString()).collect(Collectors.toList()), addition.getLanguage());
        return exerciseDTO;
    }

    public static LessonInfoDTO mapFromLessonInfo( LessonInfo lessonInfo) {
        LessonInfoDTO lessonInfoDTO = new LessonInfoDTO(lessonInfo.getId(), lessonInfo.getName());
        return lessonInfoDTO;
    }
}
