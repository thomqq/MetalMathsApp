package tq.arxsoft.metalmaths.domain;

import lombok.Data;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.operation.Exercise;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class SimpleRandomLesson implements CurrentLesson {

    private Lesson lesson;
    private List<Integer> lessonOrder;
    private Integer currentLessonIdx;

    public SimpleRandomLesson(Lesson lesson) {
        this.lesson = lesson;
        lessonOrder = IntStream.range(0, lesson.getExerciseList().size()).boxed().collect(Collectors.toList());
        Collections.shuffle(lessonOrder);
        currentLessonIdx = 0;
    }

    @Override
    public boolean isNextExercise() {
        return lesson != null && currentLessonIdx < lesson.getExerciseList().size();
    }

    @Override
    public Exercise getNextExercise() {
        if( !isNextExercise()) {
            return null;
        }
        return lesson.getExerciseList().get(currentLessonIdx++);
    }

    @Override
    public Exercise getCurrentExercise() {
        if( !isNextExercise() ) {
            return null;
        }
        return lesson.getExerciseList().get(currentLessonIdx);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return lesson.getExerciseList().get(currentLessonIdx).checkAnswer(Integer.parseInt(answer));
    }

    @Override
    public long getLessonId() {
        return lesson.getId();
    }

}
