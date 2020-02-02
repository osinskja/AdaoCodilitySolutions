package controller;

import domain.SolutionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/solutionForm")
public class SolutionFormController {

    private static Logger logger = LoggerFactory.getLogger(SolutionFormController.class);

    @GetMapping
    public String showSolutionForm(Model model) {
        model.addAttribute("solutionDto", new SolutionDto());
        return "solutionForm";
    }

    @PostMapping
    public String processSolutionForm(SolutionDto solutionDto) {
        logger.info("solutionDto:" + solutionDto.getCode() + solutionDto.getProblemDescription() + solutionDto.getTitle() + solutionDto.getSolutionDescription());

        return "redirect:/";
    }
}
