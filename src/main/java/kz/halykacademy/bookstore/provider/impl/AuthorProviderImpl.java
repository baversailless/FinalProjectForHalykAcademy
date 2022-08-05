package kz.halykacademy.bookstore.provider.impl;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.provider.AuthorProvider;

import java.util.List;

public class AuthorProviderImpl implements AuthorProvider {

    private static List<Author> authorList;

    public AuthorProviderImpl(List<Author> authorList){
        this.authorList = authorList;
    }

    @Override
    public List<Author> getAll() {
        return authorList;
    }

}
