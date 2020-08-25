package tq.arxsoft.metalmaths.exercise.controllers;

import com.sun.istack.Nullable;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tq.arxsoft.metalmaths.domain.SimpleRandomLesson;
import tq.arxsoft.metalmaths.exercise.ExerciseContext;
import tq.arxsoft.metalmaths.exercise.mappers.Mapper;
import tq.arxsoft.metalmaths.model.ExerciseDTO;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.services.LessonsService;

@Controller
@Validated
@Slf4j
public class ExerciseController {

    private ExerciseContext exerciseContext;
    private LessonsService lessonsService;

    @Autowired
    public ExerciseController(ExerciseContext exerciseContext, LessonsService lessonsService) {
        this.exerciseContext = exerciseContext;
        this.lessonsService = lessonsService;
    }

    //TODO this framgment needs more works
    @RequestMapping(value = "/request")
    public ModelAndView mainPage(@RequestParam(value = "lessonId", required = false, defaultValue = "") String lessonId) {

        if( lessonId.isEmpty() ) {
            lessonId = "" + exerciseContext.getCurrentExercise().getLessonId();
        }

        if( exerciseContext.getCurrentExercise() == null || exerciseContext.getCurrentExercise().isNextExercise() == false) {
            initCurrenExercise(Long.parseLong(lessonId));
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        Exercise operation = exerciseContext.getCurrentExercise().getNextExercise();
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exercise", exerciseDTO);
        return modelAndView;
    }

    private void initCurrenExercise(long lessonId) {
        //log.info("TQ: initCurrenExercise");
        Lesson lesson = lessonsService.getLesson(lessonId);
        exerciseContext.setCurrentExercise(new SimpleRandomLesson(lesson));
    }

    @RequestMapping("/goodAnswer")
    public ModelAndView goodAnswer(@RequestParam String answer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("answer");

        Exercise operation = exerciseContext.getCurrentExercise().getCurrentExercise();
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exercise", exerciseDTO);
        modelAndView.addObject("answer", answer);
        modelAndView.addObject("is_correct", true);
        modelAndView.addObject("message", "answer.GOOD");
        return modelAndView;
    }

    @RequestMapping("/badAnswer")
    public ModelAndView bedAnswer(@RequestParam String answer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("answer");

        Exercise operation = exerciseContext.getCurrentExercise().getCurrentExercise();
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exercise", exerciseDTO);
        modelAndView.addObject("answer", answer);
        modelAndView.addObject("is_correct", false);
        modelAndView.addObject("message", "answer.BAD");
        return modelAndView;
    }

    @RequestMapping("/answer")
    public String getAnswer(@RequestParam String answer) {
        boolean isCorrectAnswer = exerciseContext.getCurrentExercise().checkAnswer(answer);
        if( isCorrectAnswer ) {
            return "forward:/goodAnswer";
        }
        return "forward:/badAnswer";
    }


}
