package com.example.cloudinary.service.impl;

import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.model.entity.Picture;
import com.example.cloudinary.model.view.PictureViewModel;
import com.example.cloudinary.repository.PictureRepository;
import com.example.cloudinary.service.CloudinaryImage;
import com.example.cloudinary.service.CloudinaryService;
import com.example.cloudinary.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void savePicture(PictureBindingModel bindingModel) throws IOException {
        Picture picture = this.createPictureEntity(bindingModel.getPicture()
                , bindingModel.getTitle());

        this.pictureRepository.save(picture);
    }

    @Override
    public List<PictureViewModel> getAllPictures() {
        return this
                .pictureRepository
                .findAll()
                .stream()
                .map(this::asViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByPublicId(String publicId) {
        if (cloudinaryService.delete(publicId)) {
            this.pictureRepository.deleteAllByPublicId(publicId);
        }
    }

    private PictureViewModel asViewModel(Picture picture) {
        return new PictureViewModel()
                .setPublicId(picture.getPublicId())
                .setTitle(picture.getTitle())
                .setUrl(picture.getUrl());
    }

    private Picture createPictureEntity(MultipartFile picture, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(picture);
        return new Picture()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
    }


}
