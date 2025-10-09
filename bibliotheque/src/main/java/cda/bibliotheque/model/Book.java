package cda.bibliotheque.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private Date release_date;
    private boolean isAvailable;
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(int id, String title, Date release_date, boolean isAvailable) {
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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toStringAuthors() {
        String authorString = "";
        for(Author a : authors) {
            authorString = authorString + a.toString();
        }
        return authorString;
    }
}