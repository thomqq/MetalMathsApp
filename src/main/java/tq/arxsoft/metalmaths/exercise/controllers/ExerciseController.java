package tq.arxsoft.metalmaths.exercise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tq.arxsoft.metalmaths.model.ExerciseDTO;

@Controller
public class ExerciseController {

    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("exersice", new ExerciseDTO("2 +2 "));
        return modelAndView;
    }
}
