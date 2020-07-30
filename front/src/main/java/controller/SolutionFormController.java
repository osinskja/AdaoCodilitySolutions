package controller;

import data.Solution;
import domain.SolutionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repository.template.SolutionRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/solutionForm")
public class SolutionFormController {

    private static Logger logger = LoggerFactory.getLogger(SolutionFormController.class);

    @Autowired
    private SolutionRepository solutionRepository;

    @GetMapping
    public String showSolutionForm(Model model) {
        model.addAttribute("solutionDto", new SolutionDto());
        return "solutionForm";
    }

    @PostMapping
    public String processSolutionForm(@Valid SolutionDto solutionDto, Errors errors, @RequestParam("imagefile") MultipartFile image) throws IOException {
        logger.info("solutionDto:" + solutionDto.getCode() + solutionDto.getProblemDescription() + solutionDto.getTitle() + solutionDto.getSolutionDescription());
        if (errors.hasErrors()) {
            return "solutionForm";
        }
        Solution solution;
        if (StringUtils.isEmpty(solutionDto.getId())) {
            solution = new Solution();
            solution.setTimeCreated(LocalDateTime.now());
        } else {
            solution = solutionRepository.findOne(solutionDto.getId());
        }
        solution.setTitle(solutionDto.getTitle());
        solution.setProblemDescription(solutionDto.getProblemDescription());
        solution.setCode(solutionDto.getCode());
        solution.setSolutionDescription(solutionDto.getSolutionDescription());
        setImage(image, solution);

        solutionRepository.persistEntity(solution);

        return "redirect:/";
    }

    private void setImage(MultipartFile image, Solution solution) throws IOException {
        if(image.getBytes().length != 0) {
            int i = 0;
            Byte[] byteImage = new Byte[image.getBytes().length];
            for (byte b : image.getBytes()) {
                byteImage[i++] = b;
            }
            solution.setImage(byteImage);
        }
    }
}
