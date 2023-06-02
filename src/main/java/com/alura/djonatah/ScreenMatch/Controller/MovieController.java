package com.alura.djonatah.ScreenMatch.Controller;

import com.alura.djonatah.ScreenMatch.Domain.MovieUpdateData;
import com.alura.djonatah.ScreenMatch.Domain.Movie;
import com.alura.djonatah.ScreenMatch.Domain.MovieFormData;
import com.alura.djonatah.ScreenMatch.Domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("form")
    public String loadForm(Long id, Model model){
        if(id != null){
            var movie = movieRepository.getReferenceById(id);
            model.addAttribute("movie", movie);
        }

        return "movie/form.html";
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("list", movieRepository.findAll());
        return "movie/list.html";
    }

    @PostMapping
    @Transactional
    public String save(MovieFormData movieDataForm){
        Movie movie = new Movie(movieDataForm);
        System.out.println(movie);
        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @PutMapping
    @Transactional
    public String update(MovieUpdateData movieDataForm){
        var movie = movieRepository.getReferenceById(movieDataForm.id());
        movie.update(movieDataForm);
        return "redirect:/movies";
    }
    @DeleteMapping
    @Transactional
    public String delete(Long id){
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }

}
