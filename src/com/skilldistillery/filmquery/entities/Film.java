package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
    private int id;
    private String title;
    private String description;
    private Integer releaseYear;
    private int langID;
    private int rentDuration;
    private double rentRate;
    private Integer length;
    private double replaceCost;
    private String rating;
    private String specFeatures;
    private List<Actor> filmCast;

    // CONSTRUCTORS
    public Film() {
        super();
    }

    public Film(int id, String title, String description, int releaseYear, 
                int langID, int rentDuration, double rentRate, int length, 
                double replaceCost, String rating, String specFeatures) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.langID = langID;
        this.rentDuration = rentDuration;
        this.rentRate = rentRate;
        this.length = length;
        this.replaceCost = replaceCost;
        this.rating = rating;
        this.specFeatures = specFeatures;
    }

    // TO STRING
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n")
          .append("Year: ").append(releaseYear).append("\n")
          .append("Rating: ").append(rating).append("\n")
          .append("Description: ").append(description).append("\n")
          .append("Language ID: ").append(langID).append("\n")
          .append("Cast: ").append("\n");
        if (filmCast != null && !filmCast.isEmpty()) {
            for (Actor actor : filmCast) {
                sb.append("\t").append(actor.getFirstName()).append(" ").append(actor.getLastName()).append("\n");
            }
        } else {
            sb.append("\tNo cast information available.\n");
        }
        return sb.toString();
    }

    // HASH CODE
    @Override
    public int hashCode() {
        return Objects.hash(description, id, langID, length, rating, releaseYear, rentDuration, rentRate, replaceCost,
                specFeatures, title);
    }

    // EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Film other = (Film) obj;
        return id == other.id && langID == other.langID && rentDuration == other.rentDuration
                && Double.compare(other.rentRate, rentRate) == 0 && Double.compare(other.replaceCost, replaceCost) == 0
                && Objects.equals(description, other.description) && Objects.equals(length, other.length)
                && Objects.equals(rating, other.rating) && Objects.equals(releaseYear, other.releaseYear)
                && Objects.equals(specFeatures, other.specFeatures) && Objects.equals(title, other.title);
    }

    // GETTERS & SETTERS
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLangID() {
        return langID;
    }

    public void setLangID(int langID) {
        this.langID = langID;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(int rentDuration) {
        this.rentDuration = rentDuration;
    }

    public double getRentRate() {
        return rentRate;
    }

    public void setRentRate(double rentRate) {
        this.rentRate = rentRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public double getReplaceCost() {
        return replaceCost;
    }

    public void setReplaceCost(double replaceCost) {
        this.replaceCost = replaceCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecFeatures() {
        return specFeatures;
    }

    public void setSpecFeatures(String specFeatures) {
        this.specFeatures = specFeatures;
    }

    public List<Actor> getFilmCast() {
        return filmCast;
    }

    public void setFilmCast(List<Actor> filmCast) {
        this.filmCast = filmCast;
    }
}

