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
        LessonServiceFake.LessonsHelper.init();
        return LessonsHelper.id2lessons.get(id);
    }

    private static class LessonsHelper {
        static List< Lesson > lessons = new ArrayList<>();
        static HashMap< Long, Lesson > id2lessons = new HashMap<>();

        static {
            init();
        }

        static void init() {
            lessons.clear();
            id2lessons.clear();

            Random rnd = new Random(System.currentTimeMillis());

            addLesson(1, "Dodawanie 10", 10, rnd);
            addLesson(2, "Dodawanie 25", 25, rnd);
            addLesson(3, "Dodawanie 50", 50, rnd);
            addLesson(4, "Dodawanie 100", 100, rnd);

        }

        static List< LessonInfo >getLessons() {
            List<LessonInfo> lessonInfos = new ArrayList<>();
            for( Lesson lesson : lessons ) {
                lessonInfos.add(new LessonInfo(lesson.getName(), lesson.getId()));
            }
            return lessonInfos;
        }

        static void addLesson(long id, String name, int range, Random rnd) {
            Lesson lesson = new Lesson(id, name, Arrays.asList(new Addition(rnd.nextInt(range) + 1, rnd.nextInt(range) + 1)));
            id2lessons.put(lesson.getId(), lesson);
            lessons.add(lesson);
        }

    }

}
