package sg.edu.rp.c346.id22022096.songs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public song( String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() { return id;}
    public song setID(int id) {
        this.id =id;
        return this;
    }
    public String getTitle() {return title;}
    public song setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getSingers() {return singers;}
    public song setSingers(String singers) {
        this.singers = singers;
        return this;
    }
    public int getYear() {return year;}
    public song setYear(int year) {
        this.year = year;
        return this;
    }
    public int getStar() {return stars;}
    public song setStar(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String stringstar = "";
        //for (int i = 0; i < stars; i ++) {
            //stringstar = "*";
        //}
        if (stars == 1) {
            stringstar = "*";
        } else if (stars == 2) {
            stringstar = "**";
        } else if (stars == 3) {
            stringstar = "***";
        } else if (stars == 4) {
            stringstar = "****";
        } else if (stars == 5) {
            stringstar = "*****";
        }
        return title + "\n" + singers + "\n" + year + "\n" + stringstar;
    }

}
