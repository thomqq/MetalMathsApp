package tq.arxsoft.metalmaths.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.MathExercise;
import utils.FilesUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Stream;

@Service
@Slf4j
@Primary
public class LessonServiceDirectory implements LessonsService {
    private String lessonDirectory;

    @Autowired
    public LessonServiceDirectory(@Value("${lessons.dir}") String lessonDirectory) {
        this.lessonDirectory = lessonDirectory;
    }

    @Override
    public List<LessonInfo> getLessonsInfo() {
        List<LessonInfo> lessonInfos = new ArrayList<>();
        try {
            Files.list(Paths.get(lessonDirectory)).filter(Files::isDirectory).map(fileName -> parseLessonInfoFile(fileName)).forEach(
                    lessonInfo -> {
                        lessonInfos.add(lessonInfo);
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessonInfos;
    }

    private LessonInfo parseLessonInfoFile(Path fileName) {
        try {
            Path path = Paths.get(fileName.toString() + "\\info.txt");
            Map<String, String>  lessonToken = FilesUtils.linesToMap( path);
            return new LessonInfo(lessonToken.get("title"), Integer.parseInt(lessonToken.get("id")), fileName.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void linesToMap(Map<String, String> lessonToken, Path path) throws IOException {
        Files.lines(path).forEach(
                line -> {
                    String tmpLine = line.trim();
                    if (!tmpLine.startsWith("#")) {
                        String[] items = tmpLine.split(":");
                        lessonToken.put(items[0].trim().toLowerCase(), items[1].trim());
                    }
                }
        );
    }

    @Override
    public Lesson getLesson(long lessonId) {

        LessonInfo lessonInfo = getLessonsInfo().stream().filter(x -> x.getId() == lessonId).limit(1).findAny().orElse(null);
        try {
            List<Exercise> exercises = parseExercise(lessonInfo.getPath());
            Lesson result = new Lesson(lessonInfo.getId(), lessonInfo.getName(), exercises);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    List<Exercise> parseExercise(String directory) throws IOException {
        List<Exercise> exercises = new ArrayList<>();
        Files.list(Paths.get(directory)).filter(Files::isRegularFile).filter( path -> {
            return path.toString().endsWith(".txt") && !path.toString().endsWith("info.txt");
        }).forEach( file ->
                {
                    try {
                        Map<String, String> items = FilesUtils.linesToMap(file);
                        Exercise exercise = createExercise(items);
                        exercises.add(exercise);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return exercises;
    }

    Exercise createExercise(Map<String, String> items ) {
        ExerciseType exerciseType = ExerciseType.valueOf(items.get("type"));

        String[] operationItem = items.get("operation").split(" ");
        List<String> values = new ArrayList<>();
        MathExercise mathExercise = null;
        for( String item : operationItem) {
            if (!item.isEmpty() && !item.equals(" ")) {
                if (item.equals("+")) {
                    mathExercise = new Addition(exerciseType);
                    continue;
                }
                if( items.containsKey(item.toLowerCase())) {
                    values.add(items.get(item.toLowerCase()));
                }
            }
        }
        mathExercise.init(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)));

        return mathExercise;
    }

}
