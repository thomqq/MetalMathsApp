package tq.arxsoft.metalmaths.services;

import org.springframework.stereotype.Service;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.operation.Addition;

import java.util.*;

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
            Random rnd = new Random(System.currentTimeMillis());


            Lesson lesson = new Lesson(1, "Dodawanie 10", Arrays.asList(new Addition(rnd.nextInt(10) + 1, rnd.nextInt(10) + 1)));
            Lesson lesson1 = new Lesson(2, "Dodawanie 5", Arrays.asList(new Addition(rnd.nextInt(5) + 1, rnd.nextInt(5) + 1)));
            Lesson lesson2 = new Lesson(3, "Dodawanie 3", Arrays.asList(new Addition(rnd.nextInt(3) + 1, rnd.nextInt(3) + 1)));
            id2lessons.put(lesson.getId(), lesson);
            id2lessons.put(lesson1.getId(), lesson1);
            id2lessons.put(lesson2.getId(), lesson2);
            lessons.add(lesson);
            lessons.add(lesson1);
            lessons.add(lesson2);

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
