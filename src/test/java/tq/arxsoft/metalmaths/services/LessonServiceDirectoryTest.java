package tq.arxsoft.metalmaths.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tq.arxsoft.metalmaths.conf.MainConfiguration;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.FlashCard;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceDirectoryTest {

    private LessonServiceDirectory lessonServiceDirectory;

    @BeforeEach
    void init() {
        MainConfiguration mainConfiguration = new MainConfiguration();
        lessonServiceDirectory= new LessonServiceDirectory("src\\test\\java\\tq\\arxsoft\\metalmaths\\services\\lessons", mainConfiguration.getParsers());
    }

    @Test
    void ShouldGet4LessonInfosFromDirectory() {
        //when
        //given
        List<LessonInfo> lessonInfos = lessonServiceDirectory.getLessonsInfo();

        //then
        assertEquals(4, lessonInfos.size());
    }

    @Test
    void ShouldCreateMathExerciseForLessonId1() {
        //when
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
            HashMap<String, List<String>> lineItems = new HashMap<>();
            prepare(items[i], lineItems);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems).get(0);
            //then
            assertEquals(additions[i], exercise);
        }
    }

    @Test
    void shouldCreateSpellingExercise() {
        //when
        String[] items = new String[]{"id: 1|type: CardSpelling|word: {EN}RUN",
                "id: 1|type: CardSpelling|word: {PL}AVAILABLE"};
        FlashCard[] card = new FlashCard[]{new FlashCard("R,U,N,", "RUN", "EN"),
                new FlashCard("A,V,A,I,L,A,B,L,E,", "AVAILABLE", "PL")};
        for (int i = 0; i < items.length; i++) {
            //given
            HashMap<String, List<String>> lineItems = new HashMap<>();
            prepare(items[i], lineItems);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems).get(0);
            //then
            assertEquals(card[i], exercise);
        }
    }

    @Test
    void shouldCreateFlashExercise() {
        //when
        String[] items = new String[]{"id: 1|type: CardFlash|word: RUN",
                "id: 1|type: CardFlash|word: AVAILABLE"};
        FlashCard[] card = new FlashCard[]{new FlashCard("RUN", "RUN"),
                new FlashCard("AVAILABLE", "AVAILABLE")};
        for (int i = 0; i < items.length; i++) {
            //given
            HashMap<String, List<String>> lineItems = new HashMap<>();
            prepare(items[i], lineItems);
            Exercise exercise = lessonServiceDirectory.createExercise(lineItems).get(0);
            //then
            assertEquals(card[i], exercise);
        }
    }


    private void prepare(String item, HashMap<String,  List<String>> map) {
        HashMap<String, List<String>> result = new HashMap<>();
        String[] items1 = item.split("\\|");
        for (String tmp : items1) {
            String[] tmp2 = tmp.split(":");

            List<String> keys = map.get(tmp2[0].trim().toLowerCase());
            if( keys == null ) {
                keys = new ArrayList<>();
                map.put(tmp2[0].trim().toLowerCase(), keys);
            }
            keys.add(tmp2[1].trim());
        }
    }
}