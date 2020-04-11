package controller;

import data.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.template.SolutionRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private SolutionRepository solutionRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Solution> solutionsList = solutionRepository.findAll();
        model.addAttribute("solutions", solutionsList);
        return "homePage";
    }
}
