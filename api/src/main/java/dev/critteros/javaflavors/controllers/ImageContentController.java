package dev.critteros.javaflavors.controllers;

import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.critteros.javaflavors.model.Image;
import dev.critteros.javaflavors.repository.ImageContentStore;
import dev.critteros.javaflavors.repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ImageContentController {

    @Autowired
    private ImageRepository imagesRepo;
    @Autowired
    private ImageContentStore contentStore;

    @RequestMapping(value = "/images/{imageUid}", method = RequestMethod.GET)
    public ResponseEntity<?> getImage(@PathVariable("imageUid") UUID id) {

        Optional<Image> f = imagesRepo.findById(id);
        if (f.isPresent()) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f.get()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getContentLength());
            headers.set("Content-Type", f.get().getContentMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, HttpServletRequest request)
            throws IOException {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No file uploaded");
            response.put("link", null);
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        Image image = new Image();
        image.setContentMimeType(file.getContentType());
        image.setName(file.getOriginalFilename());
        image.setContentLength(file.getSize());
        contentStore.setContent(image, file.getInputStream());
        imagesRepo.save(image);

        response.put("status", "success");
        response.put("message", "File uploaded successfully");
        response.put("link", image.getResourceUrl(request));
        response.put("uid", image.getUid().toString());

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
