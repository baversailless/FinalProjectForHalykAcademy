package kz.halykacademy.bookstore.entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    private int id;
    private int price;
    private List<Author> authorList;
    private Publisher publisher;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public Book(int price, List<Author> authorList, Publisher publisher, String title, int numberOfPages, int releaseYear) {
        this.id = generatorId.getAndIncrement();
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
