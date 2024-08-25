package com.generic.uploadservice.entities;

import java.util.Objects;
import java.util.UUID;

import com.generic.uploadservice.validators.ConfigurableSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * GstnType entity
 */

@Entity
public class Gstntype {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID gstntypeid;
	@ConfigurableSize(maxProperty = "gstntype.column.size")
	private String gsttypedesc;

	public UUID getGstntypeid() {
		return gstntypeid;
	}

	public void setGstntypeid(UUID gstntypeid) {
		this.gstntypeid = gstntypeid;
	}

	public String getGsttypedesc() {
		return gsttypedesc;
	}

	public void setGsttypedesc(String gsttypedesc) {
		this.gsttypedesc = gsttypedesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gstntypeid, gsttypedesc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gstntype other = (Gstntype) obj;
		return Objects.equals(gstntypeid, other.gstntypeid) && Objects.equals(gsttypedesc, other.gsttypedesc);
	}

	@Override
	public String toString() {
		return "GstnType [gstntypeid=" + gstntypeid + ", gsttypedesc=" + gsttypedesc + "]";
	}
}
