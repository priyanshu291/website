package com.weddingapp.controller;

import com.weddingapp.model.Guest;
import com.weddingapp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/guestlist")
    public String guestList(Model model) {
        List<Guest> guests = guestRepository.findAll();
        model.addAttribute("guests", guests);

        // Static lists for cascading dropdowns (optional for JS use)
        List<String> countries = Arrays.asList("India", "USA");
        List<String> states = Arrays.asList("Bihar", "Maharashtra", "California");
        List<String> cities = Arrays.asList("Patna", "Mumbai", "Los Angeles");

        // Cast categories (used for dropdown only if you want to populate initially)
        List<String> casts = Arrays.asList("General", "OBC", "SC", "ST");

        model.addAttribute("countries", countries);
        model.addAttribute("states", states);
        model.addAttribute("cities", cities);
        model.addAttribute("casts", casts); // For future use if you want to use in Thymeleaf instead of JS
        model.addAttribute("guest", new Guest());

        return "guestlist";
    }

    @PostMapping("/guestlist")
    public String saveGuest(@ModelAttribute Guest guest) {
        guestRepository.save(guest);
        return "redirect:/guestlist";
    }

    @PostMapping("/guestlist/delete/{id}")
    public String deleteGuest(@PathVariable Long id) {
        guestRepository.deleteById(id);
        return "redirect:/guestlist";
    }
}
