package kz.halykacademy.bookstore.provider.impl;

import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.provider.PublisherProvider;

import java.util.List;

public class PublisherProviderImpl implements PublisherProvider {
    private static List<Publisher> publisherList;

    public PublisherProviderImpl(List<Publisher> publisherList){
        this.publisherList = publisherList;
    }

    @Override
    public List<Publisher> getAll() {
        return publisherList;
    }

}
