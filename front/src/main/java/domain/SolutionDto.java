package domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SolutionDto {

    private String id;

    @NotBlank(message="Title is required")
    @Size(max=50, message="Title is too long. Maximum 50 characters allowed.")
    private String title;
    @NotBlank(message="Problem description is required")
    @Size(max=600, message="Problem description is too long. Maximum 600 characters allowed.")
    private String problemDescription;
    @NotBlank(message="Code is required")
    @Size(max=2000, message="Code is too long. Maximum 2000 characters allowed.")
    private String code;
    @Size(max=600, message="Title is too long. Maximum 600 characters allowed.")
    private String solutionDescription;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getCode() {
        return code;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }
}
