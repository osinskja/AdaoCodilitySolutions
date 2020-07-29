package controller.solutionform;

import common.AbstractRepositoryTest;
import controller.TestControllerApplication;
import data.Solution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import repository.template.SolutionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {
        TestControllerApplication.class
})
@AutoConfigureMockMvc
public class SolutionFormControllerTest extends AbstractRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SolutionRepository solutionRepository;
    

    @AfterEach
    public void cleanUp() throws Exception {
      tearDown(Solution.class);
    }

    @Test
    public void solutionFormPageStartsTest() throws Exception {
        mockMvc.perform(get("/solutionForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("solutionForm"))
                .andExpect(content().string(containsString("Insert solution:")));
    }

    @Test
    public void solutionFormSolutionPersistingTest() throws Exception {
        byte[] testImageBytes = "some image".getBytes();
        MockMultipartFile testImage = new MockMultipartFile("imagefile", "image.jpg", "image/jpeg", testImageBytes);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/solutionForm")
                .file(testImage)
                .param("title", "test title")
                .param("problemDescription", "test problem description")
                .param("code", "test code")
                .param("solutionDescription", "test solution description")
        )
                .andExpect(status().is3xxRedirection());

        List<Solution> solutions = solutionRepository.findAll();
        assertThat(solutions.get(0).getTitle()).isEqualTo("test title");
        assertThat(solutions.get(0).getProblemDescription()).isEqualTo("test problem description");
        assertThat(solutions.get(0).getCode()).isEqualTo("test code");
        assertThat(solutions.get(0).getSolutionDescription()).isEqualTo("test solution description");
        assertThat(solutions.get(0).getImage()).isEqualTo(testImageBytes);
    }
}
