package com.weddingapp.repository;

import com.weddingapp.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    
    List<Photo> findByType(String type);

   
    void deleteByFileName(String fileName);
}
