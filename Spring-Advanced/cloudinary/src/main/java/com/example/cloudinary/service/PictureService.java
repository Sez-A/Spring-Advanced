package com.example.cloudinary.service;

import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.model.view.PictureViewModel;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    void savePicture(PictureBindingModel bindingModel) throws IOException;

    List<PictureViewModel> getAllPictures();

    void deleteByPublicId(String publicId);
}
