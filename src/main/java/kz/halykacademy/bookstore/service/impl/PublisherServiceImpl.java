package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.PublisherMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.PublisherService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;
    private PublisherMapper publisherMapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository,
                                PublisherMapper publisherMapper,
                                BookRepository bookRepository){
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public List<PublisherDTO> findByName(String name) {
        List<PublisherDTO> dtoList;
        List<Publisher> publishers = publisherRepository.findByName(name.toLowerCase());
        if (publishers.isEmpty()){
            throw new DataNotFoundException("NOT FOUND");
        }
        dtoList = publisherMapper.toPublisherDTOList(publishers);
        return dtoList;
    }

    @Override
    public List<PublisherDTO> getPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        if (publishers.isEmpty()){
            throw new DataNotFoundException("THERE ARE NO PUBLISHERS");
        }
        return publisherMapper.toPublisherDTOList(publishers);
    }

    @Override
    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher == null) {
            throw new DataNotFoundException("THERE IS NO PUBLISHER WITH THIS ID");
        }
        return publisherMapper.toDTO(publisherRepository.findById(id).orElse(null));
    }

    @Override
    public void createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        Publisher temp = publisherRepository.checkExistedPublisher(publisher.getName());
        if (temp != null){
            throw new CreateDataFailException("PUBLISHER ALREADY EXISTS");
        }
        publisherRepository.saveAndFlush(publisher);
    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO) {
        if (publisherDTO.getId() == null){
            throw new UpdateDataFailException("YOU NEED TO SPECIFY PUBLISHER ID");
        }
        Publisher found = publisherRepository.findById(publisherDTO.getId()).orElse(null);
        if (found == null){
            throw new UpdateDataFailException("THERE IS NO PUBLISHER WITH THIS ID");
        }

        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisherRepository.saveAndFlush(publisher);
        return publisherDTO;
    }


    @Override
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if(publisher == null){
            throw new DataNotFoundException("THERE IS NO PUBLISHER WITH THIS ID");
        }
        publisherRepository.deleteById(id);
    }

}
