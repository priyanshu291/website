package com.weddingapp.controller;

import com.weddingapp.entity.Gift;
import com.weddingapp.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GiftController {

    @Autowired
    private GiftRepository giftRepository;

    @GetMapping("/gift")
    public String showGiftForm(Model model) {
        List<Gift> gifts = new ArrayList<>();
        giftRepository.findAll().forEach(gifts::add);
        model.addAttribute("gifts", gifts);
        model.addAttribute("gift", new Gift());
        return "gift";
    }

    @PostMapping("/gift")
    public String saveGift(@ModelAttribute Gift gift) {
        giftRepository.save(gift);
        return "redirect:/gift";
    }

    // ✅ Updated to GET for link-based delete
    @GetMapping("/gift/delete/{id}")
    public String deleteGift(@PathVariable Long id) {
        giftRepository.deleteById(id);
        return "redirect:/gift";
    }
}
