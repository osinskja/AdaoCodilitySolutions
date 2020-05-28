package controller;

import data.Solution;
import domain.SolutionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.template.SolutionRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/solutionEditor")
public class SolutionEditorController {

    @Autowired
    private SolutionRepository solutionRepository;

    @GetMapping
    public String solutionEditor(Model model) {
        List<Solution> solutionsList = solutionRepository.findAll();
        model.addAttribute("solutions", solutionsList);
        return "solutionEditor";
    }

    @PostMapping("/solutionDelete")
    public String solutionDelete(Solution solutionToDelete) {
        solutionRepository.deleteEntity(solutionToDelete);
        return "redirect:/solutionEditor";
    }

    @PostMapping("/solutionEdit")
    public String solutionEdit(Solution solutionToEdit, Model model) {
        SolutionDto solutionToEditDto = new SolutionDto();
        solutionToEditDto.setId(solutionToEdit.getId());
        solutionToEditDto.setCode(solutionToEdit.getCode());
        solutionToEditDto.setProblemDescription(solutionToEdit.getProblemDescription());
        solutionToEditDto.setTitle(solutionToEdit.getTitle());
        solutionToEditDto.setSolutionDescription(solutionToEdit.getSolutionDescription());
        model.addAttribute("solutionDto", solutionToEditDto);
        return "solutionForm";
    }
}
