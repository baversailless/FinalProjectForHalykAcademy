package kz.halykacademy.bookstore.dto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AuthorDTO {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymicName;
    private String dateOfBirth;
    private List<BookDTO> bookList;
    private static AtomicInteger generatorId = new AtomicInteger(0);

    public AuthorDTO(String lastName, String firstName, String patronymicName, String dateOfBirth, List<BookDTO> bookList){
        this.id = generatorId.getAndIncrement();
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.dateOfBirth = dateOfBirth;
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

}
