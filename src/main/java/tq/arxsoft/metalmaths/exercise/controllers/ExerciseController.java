package tq.arxsoft.metalmaths.exercise.controllers;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tq.arxsoft.metalmaths.exercise.ExerciseContext;
import tq.arxsoft.metalmaths.exercise.mappers.Mapper;
import tq.arxsoft.metalmaths.model.ExerciseDTO;
import tq.arxsoft.metalmaths.model.Lesson;
import tq.arxsoft.metalmaths.operation.Addition;
import tq.arxsoft.metalmaths.operation.Exercise;

@Controller
@Validated
public class ExerciseController {

    private ExerciseContext exerciseContext;

    @Autowired
    public ExerciseController(ExerciseContext ex6erciseContext) {
        this.exerciseContext = exerciseContext;
    }
    @RequestMapping("/request")
    public ModelAndView mainPage(@PathVariable("lessonId") @Nullable String lessonId) {

        //Lesson lesson = exerciseContext.

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        Exercise operation = new Addition(2,2);
        exerciseContext.setCurrentExercise(operation);
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exersice", exerciseDTO);
        return modelAndView;
    }

    @RequestMapping("/goodAnswer")
    public ModelAndView goodAnswer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("answer :: content");

        Exercise operation = new Addition(2,2);
        exerciseContext.setCurrentExercise(operation);
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exersice", exerciseDTO);
        modelAndView.addObject("message", "GOOD");
        return modelAndView;
    }

    @RequestMapping("/badAnswer")
    public ModelAndView bedAnswer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("answer :: content");

        Exercise operation = new Addition(2,2);
        exerciseContext.setCurrentExercise(operation);
        ExerciseDTO exerciseDTO = Mapper.mapFromAddition(operation);

        modelAndView.addObject("exersice", exerciseDTO);
        modelAndView.addObject("message", "BAD");
        return modelAndView;
    }

    @RequestMapping("/answer")
    public String getAnswer(@RequestParam String answer) {
        boolean isCorrectAnswer = exerciseContext.getCurrentExercise().checkAnswer(Integer.parseInt(answer));
        if( isCorrectAnswer ) {
            return "forward:/goodAnswer";
        }
        return "forward:/badAnswer";
    }


}
