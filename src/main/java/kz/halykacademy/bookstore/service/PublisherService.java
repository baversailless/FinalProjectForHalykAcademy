package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getPublishers();
    PublisherDTO getPublisherById(Long id);
    void createPublisher(PublisherDTO publisherDTO);
    PublisherDTO updatePublisher(PublisherDTO publisherDTO);
    void deletePublisher(Long id);
    List<PublisherDTO> findByName(String name);
}
