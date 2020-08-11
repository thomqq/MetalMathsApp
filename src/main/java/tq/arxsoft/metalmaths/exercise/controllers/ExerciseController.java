package tq.arxsoft.metalmaths.exercise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tq.arxsoft.metalmaths.exercise.mappers.ExercicesMap;
import tq.arxsoft.metalmaths.model.ExerciseDTO;
import tq.arxsoft.metalmaths.operation.Addition;

@Controller
public class ExerciseController {

    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        Addition addition = new Addition(2,2);
        ExerciseDTO exerciseDTO = ExercicesMap.mapFromAddition(addition);

        modelAndView.addObject("exersice", exerciseDTO);
        return modelAndView;
    }
}
