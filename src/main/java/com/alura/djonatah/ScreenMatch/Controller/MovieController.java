package com.alura.djonatah.ScreenMatch.Controller;

import com.alura.djonatah.ScreenMatch.Model.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();
    @GetMapping("form")
    public String loadForm(){
        return "movie/form.html";
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("list",movies);
        return "movie/list.html";
    }

    @PostMapping
    public String save(Movie movie){
        movies.add(movie);
        return "redirect:/movies";
    }
}
