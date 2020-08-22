package tq.arxsoft.metalmaths.exercise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    String main() {
        return "redirect:/lessons";
    }
}
