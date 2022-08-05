package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.entity.Author;

import java.util.List;

public interface AuthorProvider {
    public List<Author> getAll();
}
