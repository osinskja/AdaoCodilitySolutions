package controller.solutioneditor;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.AbstractRepositoryTest;
import controller.SolutionEditorController;
import controller.SolutionFormController;
import controller.TestControllerApplication;
import data.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import repository.template.SolutionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {
        TestControllerApplication.class
})
@WebMvcTest(SolutionEditorController.class)
public class SolutionEditorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void solutionEditorPageStartsTest() throws Exception {

        mockMvc.perform(post("/solutionEditor/solutionEdit")
                .param("id", "testid")
                .param("title", "test title")
                .param("problemDescription", "test problem description")
                .param("code", "test code")
                .param("solutionDescription", "test solution description")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("solutionForm"))
                .andExpect(content().string(containsString("name=\"title\" value=\"test title\"")))
                .andExpect(content().string(containsString("id=\"problemDescription\">test problem description")))
                .andExpect(content().string(containsString("id=\"code\">test code")))
                .andExpect(content().string(containsString("id=\"solutionDescription\">test solution description")));
    }

}
