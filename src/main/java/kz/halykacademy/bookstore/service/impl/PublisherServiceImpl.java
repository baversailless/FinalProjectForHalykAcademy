package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(int id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(int id, Publisher updatedPublisher) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        publisher.setName(updatedPublisher.getName());
        publisher.setBookList(updatedPublisher.getBookList());
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(int id) {
        publisherRepository.deleteById(id);
    }
}
