package de.uni_passau.facultyinfo.client.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactPerson {
	private String id;
	private String name;
	private String office;
	private String phone;
	private String description;

	@JsonCreator
	public ContactPerson(@JsonProperty("name") String name,
			@JsonProperty("office") String office,
			@JsonProperty("phone") String phone,
			@JsonProperty("description") String description) {
		super();
		this.name = name;
		this.office = office;
		this.phone = phone;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}