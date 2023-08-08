package sg.edu.rp.c346.id22043300.mymovies;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String genre;
    private int year;

    private String Rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public Movie(int id, String title, String genre, int year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        Rating = rating;
    }

    public void updateDetails(String title, String genre, int year, String rating)
    {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        Rating = rating;
    }

}
