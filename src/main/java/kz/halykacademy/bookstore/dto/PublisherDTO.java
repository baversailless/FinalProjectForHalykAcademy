package kz.halykacademy.bookstore.dto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PublisherDTO {
    private int id;
    private String name;
    private List<BookDTO> bookList;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public PublisherDTO(String name, List<BookDTO> bookList){
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

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }


}
