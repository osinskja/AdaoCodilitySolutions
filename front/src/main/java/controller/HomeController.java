package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import repository.template.SolutionRepository;


@Controller
public class HomeController {

    @Autowired
    public SolutionRepository solutionRepository;

    @GetMapping("/")
    public String homePage(){
        solutionRepository.findAll();
        return "homePage";
    }
}
