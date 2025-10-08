package cda.bibliotheque.model;

import java.time.LocalDate;
import java.sql.Date;

public class Author {
    private int id;
    private String lastName;
    private String firstName;
    private LocalDate born_at;

    public Author() {
    }

    public Author(int id, String lastName, String firstName, LocalDate born_at) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.born_at = born_at;
    }

    public Author(int id, String lastName, String firstName, Date born_at) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.born_at = born_at.toLocalDate();
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBorn_at() {
        return born_at;
    }

    public Date getBorn_at_Date() {
        return Date.valueOf(born_at);
    }

    public void setBorn_at(Date born_at) {
        this.born_at = born_at.toLocalDate();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBorn_at(LocalDate born_at) {
        this.born_at = born_at;
    }
}

