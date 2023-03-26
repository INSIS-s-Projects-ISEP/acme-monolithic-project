package com.isep.acme.controllers;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.isep.acme.model.ProdImage;
import com.isep.acme.model.Product;
import com.isep.acme.property.UploadFileResponse;
import com.isep.acme.repositories.ImageRepository;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.FileStorageService;

@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageRepository iRepo;

    @Autowired
    private ProductRepository pRepo;


    @GetMapping(value = "/fileid/{id}")
    public ResponseEntity<ProdImage> findById(@PathVariable("id") final Long id){

        final var prodImage = iRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Not Found"));


        return  ResponseEntity.ok().body(prodImage);
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/ID/{productID}")
    public ResponseEntity<Product> findByID(@PathVariable("productID") final Long productID){
        final var product = pRepo.findById(productID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        return ResponseEntity.ok().body(product);
    }




}