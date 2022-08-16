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
    public ResponseEntity<ResponseDTO> findByTitle(@PathVariable("name") String name){
        ResponseDTO response = new ResponseDTO();
        try {
            List<PublisherDTO> listDTO = publisherService.findByName(name);
            response.setData(listDTO);
            response.setSuccessCode("GET ALL PUBLISHERS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("PUBLISHERS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDTO> getAllPublishers() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<PublisherDTO> listDTO = publisherService.getPublishers();
            response.setData(listDTO);
            response.setSuccessCode("GET ALL PUBLISHERS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("PUBLISHERS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("publisher/{publisherId}")
    public ResponseEntity<ResponseDTO> getPublisher(@PathVariable("publisherId") Long publisherId) {
        ResponseDTO response = new ResponseDTO();
        try {
            PublisherDTO dto = publisherService.getPublisherById(publisherId);
            response.setData(dto);
            response.setSuccessCode("GET PUBLISHER SUCCESSFULLY");
        } catch (Exception e) {
            throw new DataNotFoundException("PUBLISHER NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/publisher/create", consumes = "application/json")
    public ResponseEntity<ResponseDTO> createNewPublisher(@RequestBody PublisherDTO publisherDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            publisherService.createPublisher(publisherDTO);
            response.setSuccessCode("CREATE PUBLISHER SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new CreateDataFailException("SOMETHING WENT WRONG DURING createPublisher");
        }
        return ResponseEntity.ok().body(response);
    }


    @PutMapping(value = "/publisher/update", consumes = "application/json")
    public ResponseEntity<ResponseDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            PublisherDTO dto = publisherService.updatePublisher(publisherDTO);
            response.setData(dto);
            response.setSuccessCode("UPDATE PUBLISHER SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new UpdateDataFailException("Something went wrong with updatePublisher");
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/publisher/delete/{publisherId}")
    public ResponseEntity<ResponseDTO> deletePublisher(@PathVariable("publisherId") Long publisherId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            publisherService.deletePublisher(publisherId);
            response.setSuccessCode("DELETE PUBLISHER SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DeleteDataFailException("Something went wrong with deleteBook");
        }
        return ResponseEntity.ok().body(response);
    }

}
