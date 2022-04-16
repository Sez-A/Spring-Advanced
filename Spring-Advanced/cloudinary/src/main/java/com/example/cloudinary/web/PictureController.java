package com.example.cloudinary.web;

import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @GetMapping("/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("/add")
    public String addPictureConfirm(PictureBindingModel bindingModel) throws IOException {
        this.pictureService.savePicture(bindingModel);
        return "redirect:/pictures/all";
    }

    @GetMapping("/all")
    public String allPictures(Model model) {
        model.addAttribute("pictures", this.pictureService.getAllPictures());
        return "all";
    }

    @Transactional
    @DeleteMapping("/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        pictureService.deleteByPublicId(publicId);

        return "redirect:/pictures/all";
    }
}
