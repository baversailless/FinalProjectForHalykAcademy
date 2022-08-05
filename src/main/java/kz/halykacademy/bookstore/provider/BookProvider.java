package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.entity.Book;

import java.util.List;

public interface BookProvider {
    public List<Book> getAll();
}
