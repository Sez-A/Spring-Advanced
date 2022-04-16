package com.example.cloudinary.repository;

import com.example.cloudinary.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    void deleteAllByPublicId(String publicId);
}
