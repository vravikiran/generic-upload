package com.generic.uploadservice.entities;

import java.util.Objects;
import java.util.UUID;

import com.generic.uploadservice.validators.ConfigurableSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID brandpk;
	@ConfigurableSize(maxProperty = "brandcode.column.size",message="brandcode column size is not within range")
	private String brandcode;
	@ConfigurableSize(maxProperty = "brandname.column.size", message="brandname column size is not within range")
	private String brandname;
	
	public UUID getBrandpk() {
		return brandpk;
	}

	public void setBrandpk(UUID brandpk) {
		this.brandpk = brandpk;
	}

	public String getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandcode, brandname, brandpk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return Objects.equals(brandcode, other.brandcode) && Objects.equals(brandname, other.brandname)
				&& Objects.equals(brandpk, other.brandpk);
	}

	@Override
	public String toString() {
		return "Brand [brandpk=" + brandpk + ", brandcode=" + brandcode + ", brandname=" + brandname + "]";
	}

	public Brand() {
		super();
	}
}
