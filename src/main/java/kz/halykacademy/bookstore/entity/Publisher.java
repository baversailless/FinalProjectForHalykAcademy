package kz.halykacademy.bookstore.entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Publisher {
    private int id;
    private String name;
    private List<Book> bookList;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public Publisher(String name, List<Book> bookList){
        this.id = generatorId.getAndIncrement();
        this.name = name;
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
