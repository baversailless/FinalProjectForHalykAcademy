package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getPublishers();
    Publisher getPublisherById(int id);
    void createPublisher(Publisher publisher);
    void updatePublisher(int id, Publisher publisher);
    void deletePublisher(int id);
}
