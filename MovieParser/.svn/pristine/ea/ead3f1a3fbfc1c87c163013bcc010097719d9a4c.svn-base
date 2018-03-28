package com.company;


/**
 * Created by Yuyang on 30/1/2017.
 */
public class Movie {

    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private Boolean video;
    private double vote_average;

    public boolean containsGenre (int id) {
        for (int index = 0 ; index < genre_ids.length; index ++) {
            if(id == genre_ids[index]) return true;
        }
        return false;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if ( !(obj instanceof Movie) ) {
            return false;
        }

        Movie other = (Movie) obj;
        if ( !(other.genre_ids.length == genre_ids.length) ) {
            return false;
        }
        for (int i = 0; i < genre_ids.length; i++) {
            if( !(genre_ids[i] == other.genre_ids[i]) ) {
                return false;
            }
        }
        return other.poster_path.equals(poster_path) &&
                other.adult.equals(adult) &&
                other.overview.equals(overview) &&
                other.release_date.equals(release_date) &&
                other.id == id &&
                other.original_title.equals(original_title) &&
                other.original_language.equals(original_language) &&
                other.title.equals(title) &&
                other.backdrop_path.equals(backdrop_path) &&
                other.video.equals(video) &&
                other.popularity == popularity &&
                other.vote_count == vote_count &&
                other.vote_average == vote_average;
    }



}
