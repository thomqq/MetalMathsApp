package tq.arxsoft.metalmaths.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRandomLessonTest {

    private Lesson lesson;

    @BeforeEach
    void prepareLesson(){
         lesson = new Lesson(1, "some", Arrays.asList(new Exercise[] {new Addition(2, 3, ExerciseType.MathAnswer),new Addition(2, 3, ExerciseType.MathAnswer), new Addition(2, 3, ExerciseType.MathAnswer)}));
    }

    @Test
    void ShouldCreateLessonWithAllExercisetes() {

        //given
        SimpleRandomLesson simpleRandomLesson = new SimpleRandomLesson(lesson);

        //when
        List< Integer > lessonOrder = simpleRandomLesson.getLessonOrder();

        //then
        assertEquals(lesson.getExerciseList().size(), lessonOrder.size());
    }

    @Test
    void ShouldHaveNextExerciseTimes() {
        //given
        CurrentLesson currentLesson = new SimpleRandomLesson(lesson);

        //when

        //then
        for( int i = 0; i < lesson.getExerciseList().size() ; ++i){
            assertEquals( true, currentLesson.isNextExercise() );
            assertNotNull(currentLesson.getNextExercise());
        }
        assertEquals(false, currentLesson.isNextExercise());
        assertNull(currentLesson.getNextExercise());
    }


}