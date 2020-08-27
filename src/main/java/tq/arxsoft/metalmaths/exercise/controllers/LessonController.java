package tq.arxsoft.metalmaths.exercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tq.arxsoft.metalmaths.exercise.ExerciseContext;
import tq.arxsoft.metalmaths.model.LessonInfo;
import tq.arxsoft.metalmaths.model.LessonInfoDTO;
import tq.arxsoft.metalmaths.services.LessonsService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LessonController {

    private LessonsService lessonsService;
    private ExerciseContext exerciseContext;

    @Autowired
    public LessonController(LessonsService lessonsService, ExerciseContext exerciseContext) {
        this.lessonsService = lessonsService;
        this.exerciseContext = exerciseContext;
    }

    @RequestMapping("/lessons")
    ModelAndView getLesssons() {
        List<LessonInfo> lessonsInfos = lessonsService.getLessonsInfo();

        List<LessonInfoDTO> lessonInfoDTO = lessonsInfos.stream().map(lessonInfo -> new LessonInfoDTO(lessonInfo.getId(), lessonInfo.getName())).collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lessonInfo", lessonInfoDTO);

        modelAndView.setViewName("lessons");
        return modelAndView;
    }
}
