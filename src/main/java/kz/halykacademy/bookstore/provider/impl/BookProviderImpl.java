package kz.halykacademy.bookstore.provider.impl;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.provider.BookProvider;

import java.util.List;

public class BookProviderImpl implements BookProvider {
    private static List<Book> bookList;

    public BookProviderImpl(List<Book> bookList){
        this.bookList = bookList;
    }

    @Override
    public List<Book> getAll() {
        return bookList;
    }
}
