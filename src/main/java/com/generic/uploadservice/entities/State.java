package com.generic.uploadservice.entities;

import java.util.Objects;
import java.util.UUID;

import com.generic.uploadservice.validators.ConfigurableSize;
import com.generic.uploadservice.validators.DynamicNumColSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * State entity
 */
@Entity
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID stateid;
	@DynamicNumColSize(maxProperty = "statecode.column.size")
	private int statecode;
	@ConfigurableSize(maxProperty = "statename.column.size")
	private String statename;

	public UUID getStateid() {
		return stateid;
	}

	public void setStateid(UUID stateid) {
		this.stateid = stateid;
	}

	public int getStatecode() {
		return statecode;
	}

	public void setStatecode(int statecode) {
		this.statecode = statecode;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	@Override
	public int hashCode() {
		return Objects.hash(statecode, stateid, statename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return statecode == other.statecode && Objects.equals(stateid, other.stateid)
				&& Objects.equals(statename, other.statename);
	}

	@Override
	public String toString() {
		return "State [stateid=" + stateid + ", statecode=" + statecode + ", statename=" + statename + "]";
	}
}
