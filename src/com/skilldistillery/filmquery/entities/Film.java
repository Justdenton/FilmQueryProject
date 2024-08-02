package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private Integer releaseYear; 	// int cannot be null.
	private int langID;
	private int rentDuration;
	private double rentRate;
	private Integer length;
	private double replaceCost;
	private String rating;
	private String specFeatures;
	private List<Actor> filmCast; // #4
	
	public Film() {
		super();
	}
	
	// CONSTRUCTOR
	public Film(int id, String title, String description, int releaseYear, int langID, int rentDuration,
			double rentRate, int length, double replaceCost, String rating, String specFeatures) {
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
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", langID=" + langID + ", rentDuration=" + rentDuration + ", rentRate=" + rentRate + ", length="
				+ length + ", replaceCost=" + replaceCost + ", rating=" + rating + ", specFeatures=" + specFeatures
				+ "]";
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && langID == other.langID
				&& length == other.length && Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentDuration == other.rentDuration
				&& Double.doubleToLongBits(rentRate) == Double.doubleToLongBits(other.rentRate)
				&& Double.doubleToLongBits(replaceCost) == Double.doubleToLongBits(other.replaceCost)
				&& Objects.equals(specFeatures, other.specFeatures) && Objects.equals(title, other.title);
	}
	
	
	
	
}
