package tq.arxsoft.metalmaths.services;

import org.assertj.core.internal.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Test;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.FlashCard;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceDirectoryTest {

    private LessonServiceDirectory lessonServiceDirectory = new LessonServiceDirectory("src\\test\\java\\tq\\arxsoft\\metalmaths\\services\\lessons");

    @Test
    void ShouldGet4LessonInfosFromDirectory() {
        //when
        LessonServiceDirectory lessonServiceDirectory = new LessonServiceDirectory("src\\test\\java\\tq\\arxsoft\\metalmaths\\services\\lessons");

        //given
        List<LessonInfo> lessonInfos = lessonServiceDirectory.getLessonsInfo();

        //then
        assertEquals(4, lessonInfos.size());
    }

    @Test
    void ShouldCreateMathExerciseForLessonId1() {
        //when
        //LessonServiceDirectory lessonServiceDirectory = new LessonServiceDirectory("src\\test\\java\\tq\\arxsoft\\metalmaths\\services\\lessons");

        //given
        Lesson lesson = lessonServiceDirectory.getLesson(1);

        List<Exercise> exerciseList = lesson.getExerciseList();

        //then
        assertEquals(1, lesson.getExerciseList().size());
        assertEquals(exerciseList.get(0), new Addition(10, 10, ExerciseType.MathInput));
    }

    @Test
    void shouldCreateExercise() {
        //when
        String[] items = new String[]{"id: 1|type: MathAnswer|operation: L1 + L2|L1: 100|L2: 100",
                "id: 1|type: MathAnswer|operation: L1 + L2|L1: 22|L2: 13"};
        Addition[] additions = new Addition[]{new Addition(100, 100, ExerciseType.MathAnswer),
                new Addition(22, 13, ExerciseType.MathAnswer)};
        for (int i = 0; i < items.length; i++) {
            //given
            HashMap<String, String> lineItems = prepare(items[i]);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems);
            //then
            assertEquals(additions[i], exercise);
        }
    }

    @Test
    void shouldCreateSpellingExercise() {
        //when
        String[] items = new String[]{"id: 1|type: CardSpelling|word: RUN",
                "id: 1|type: CardSpelling|word: AVAILABLE"};
        FlashCard[] card = new FlashCard[]{new FlashCard("R,U,N,", "RUN"),
                new FlashCard("A,V,A,I,L,A,B,L,E,", "AVAILABLE")};
        for (int i = 0; i < items.length; i++) {
            //given
            HashMap<String, String> lineItems = prepare(items[i]);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems);
            //then
            assertEquals(card[i], exercise);
        }
    }

    @Test
    void shouldCreateFlashExercise() {
        //when
        String[] items = new String[]{"id: 1|type: CardFalsh|word: RUN",
                "id: 1|type: CardFalsh|word: AVAILABLE"};
        FlashCard[] card = new FlashCard[]{new FlashCard("RUN", "RUN"),
                new FlashCard("AVAILABLE", "AVAILABLE")};
        for (int i = 0; i < items.length; i++) {
            //given
            HashMap<String, String> lineItems = prepare(items[i]);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems);
            //then
            assertEquals(card[i], exercise);
        }
    }


    private HashMap<String, String> prepare(String item) {
        HashMap<String, String> result = new HashMap<>();
        String[] items1 = item.split("\\|");
        for (String tmp : items1) {
            String[] tmp2 = tmp.split(":");
            result.put(tmp2[0].trim().toLowerCase(), tmp2[1].trim());
        }
        return result;
    }
}