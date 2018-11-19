package net.skhu.model;

import java.util.Date;

import lombok.Data;

@Data
public class Person {
	int id;
	String name;
	Date birthDate;
	Double score;
	boolean enabled;

	public Person(int id, String name, Date birthDate, Double score, boolean enabled) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.score = score;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
