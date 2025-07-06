package com.weddingapp.controller;

import com.weddingapp.entity.Photo;
import com.weddingapp.repository.PhotoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class AlbumController {

    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private PhotoRepository photoRepository;

   
    @GetMapping("/album/{type}")
    public String showAlbum(@PathVariable String type, Model model) {
        List<Photo> photos = photoRepository.findByType(type);
        model.addAttribute("type", type);
        model.addAttribute("photos", photos);
        return "album";
    }

   
    @PostMapping("/album/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file,
                               @RequestParam("type") String type,
                               HttpServletRequest request) throws IOException {
        if (!file.isEmpty()) {
            
            String uploadPath = new File(UPLOAD_DIR).getAbsolutePath();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

           
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destination = new File(uploadPath + File.separator + filename);
            file.transferTo(destination);

           
            Photo photo = new Photo();
            photo.setFileName(filename);
            photo.setType(type);
            photoRepository.save(photo);
        }

        return "redirect:/album/" + type;
    }

    
    @PostMapping("/album/delete")
    @Transactional
    public String deletePhoto(@RequestParam String fileName,
                              @RequestParam String type) {
        try {
           
            Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
            Files.deleteIfExists(path);

           
            photoRepository.deleteByFileName(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/album/" + type;
    }
}
