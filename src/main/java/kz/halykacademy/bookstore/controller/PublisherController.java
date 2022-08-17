package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.dto.ResponseDTO;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @GetMapping(value = "publisher/findByName/{name}")
    public List<PublisherDTO> findByTitle(@PathVariable("name") String name) {
        return publisherService.findByName(name);
    }

    @GetMapping(value = "/all")
    public List<PublisherDTO> getAllPublishers() {
        return publisherService.getPublishers();
    }

    @GetMapping("publisher/{publisherId}")
    public PublisherDTO getPublisher(@PathVariable("publisherId") Long publisherId) {
        return publisherService.getPublisherById(publisherId);
    }

    @PostMapping(value = "/publisher/create", consumes = "application/json")
    public void createNewPublisher(@RequestBody PublisherDTO publisherDTO){
            publisherService.createPublisher(publisherDTO);
    }


    @PutMapping(value = "/publisher/update", consumes = "application/json")
    public PublisherDTO updatePublisher(@RequestBody PublisherDTO publisherDTO){
        return publisherService.updatePublisher(publisherDTO);
    }

    @DeleteMapping("/publisher/delete/{publisherId}")
    public void deletePublisher(@PathVariable("publisherId") Long publisherId) {
            publisherService.deletePublisher(publisherId);
    }

}
