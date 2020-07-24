package com.example.demo.service;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void save(Image image){
        imageRepository.save(image);
    }

    public List<Image> grtImageByCategory(int id){
       return imageRepository.findAllByCategory_Id(id);
    }
}
