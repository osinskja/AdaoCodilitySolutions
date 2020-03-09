package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String homePage(){
        jdbcTemplate.execute("DROP TABLE books IF EXISTS");
        return "homePage";
    }
}
