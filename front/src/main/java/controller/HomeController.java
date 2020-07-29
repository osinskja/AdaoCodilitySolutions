package controller;

import data.Solution;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import repository.template.SolutionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/imageDisplay/{id}")
    public void showImage(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        Solution item = solutionRepository.findOne(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if (item.getImage() != null) {
            response.getOutputStream().write(ArrayUtils.toPrimitive(item.getImage()));
        }

        response.getOutputStream().close();
    }
}
