package tq.arxsoft.metalmaths.services;

import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;

import java.util.List;

public interface LessonsService {
    List<LessonInfo> getLessonsInfo();
    Lesson getLesson(long id);
}
