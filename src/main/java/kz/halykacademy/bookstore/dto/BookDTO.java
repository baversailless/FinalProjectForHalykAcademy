package kz.halykacademy.bookstore.dto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class BookDTO {
    private long id;
    private int price;
    private List<AuthorDTO> authorList;
    private PublisherDTO publisher;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public BookDTO(int price, List<AuthorDTO> authorList, PublisherDTO publisher, String title, int numberOfPages, int releaseYear) {
        this.id = generatorId.getAndIncrement();
        this.price = price;
        this.authorList = authorList;
        this.publisher = publisher;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
    }

    public long getId() {
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

    public List<AuthorDTO> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDTO> authorList) {
        this.authorList = authorList;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
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
