package com.isep.acme.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.isep.acme.dto.ImageDTO;
import com.isep.acme.model.ProdImage;
import com.isep.acme.repositories.ImageRepository;

public class ImageService {

     @Autowired
     private FileStorageService service;

     @Autowired
     private ImageRepository repository;

     private String filename;

     public Iterable<ImageDTO> getImageProduct() {
          Iterable<ProdImage> p = repository.findAll();
          List<ImageDTO> iDto = new ArrayList<>();
          for (ProdImage pd : p) {
               iDto.add(pd.toDto());
          }

          return iDto;
     };

     public <T> Resource addImage(Resource image) {
          image = service.loadFileAsResource(filename);
          return image;
     }

}
