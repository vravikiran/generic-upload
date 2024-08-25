package com.generic.uploadservice.entities;

import java.util.Objects;
import java.util.UUID;

import com.generic.uploadservice.validators.ConfigurableSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * Rating entity
 */
@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID ratingid;
	@ConfigurableSize(maxProperty = "ratingtype.column.size")
	private String rating;

	public UUID getRatingid() {
		return ratingid;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rating, ratingid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return Objects.equals(rating, other.rating) && Objects.equals(ratingid, other.ratingid);
	}

	@Override
	public String toString() {
		return "Rating [ratingid=" + ratingid + ", rating=" + rating + "]";
	}
}
