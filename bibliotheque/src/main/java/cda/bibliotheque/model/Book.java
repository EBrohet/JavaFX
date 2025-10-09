package cda.bibliotheque.model;

import java.time.LocalDate;
import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private Date release_date;
    private short isAvailable;

    public Book() {
    }

    public Book(int id, String title, Date release_date, short isAvailable) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public short getIsAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setIsAvailable(short isAvailable) {
        this.isAvailable = isAvailable;
    }
}