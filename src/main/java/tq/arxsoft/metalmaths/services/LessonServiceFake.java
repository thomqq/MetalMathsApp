package tq.arxsoft.metalmaths.services;

import org.springframework.stereotype.Service;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.operation.Addition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class LessonServiceFake implements LessonsService {
    @Override
    public List<LessonInfo> getLessonsInfo() {
        return LessonsHelper.getLessons();
    }

    @Override
    public Lesson getLesson(long id) {
        return LessonsHelper.id2lessons.get(id);
    }

    private static class LessonsHelper {
        static List< Lesson > lessons = new ArrayList<>();
        static HashMap< Long, Lesson > id2lessons = new HashMap<>();
        static {
            Lesson lesson = new Lesson(1, "Dodawanie", Arrays.asList(new Addition(10, 10)));
            Lesson lesson1 = new Lesson(1, "Dodawanie", Arrays.asList(new Addition(5, 5)));
            Lesson lesson2 = new Lesson(1, "Dodawanie", Arrays.asList(new Addition(3, 3)));
            id2lessons.put(lesson.getId(), lesson);
            id2lessons.put(lesson1.getId(), lesson1);
            id2lessons.put(lesson2.getId(), lesson2);
            lessons.add(lesson);

        }

        static List< LessonInfo >getLessons() {
            List<LessonInfo> lessonInfos = new ArrayList<>();
            for( Lesson lesson : lessons ) {
                lessonInfos.add(new LessonInfo(lesson.getName(), lesson.getId()));
            }
            return lessonInfos;
        }

    }

}
