package com.cybersoft.uniclub06.Controller;

import com.cybersoft.uniclub06.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    FilesStorageService filesStorageService;

    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) {
        String message = "";
        try {
            filesStorageService.save(file);
            message = "success" + file.getOriginalFilename();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
