package com.alura.djonatah.ScreenMatch.Controller;

import com.alura.djonatah.ScreenMatch.Model.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();
    @GetMapping
    public String loadForm(){
        return "movie/form.html";
    }

    @PostMapping
    public String saveForm(Movie movie){
        movies.add(movie);
        System.out.println(movies);
        return "movie/form.html";
    }

}
