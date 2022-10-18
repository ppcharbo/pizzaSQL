package pizzaSQL.model;

import java.sql.Timestamp;

public class Rider {

	/*
	 * 
	 * +-------------+-------------+------+-----+---------+-------+ | Field | Type | Null | Key | Default | Extra | +-------------+-------------+------+-----+---------+-------+ | id | int | NO | PRI | NULL | | | name | varchar(45) | YES | | NULL | | | cameBack | timestamp | YES | | NULL | | | available
	 * | tinyint | NO | | 0 | | | postal_code | int | NO | | NULL | | +-------------+-------------+------+-----+---------+-------+
	 * 
	 */

	Integer id;
	String name;
	Boolean available;
	Integer postalCode;
	private Timestamp cameBack;

	public Rider(Integer id, String name, Timestamp cameBack, Boolean available, Integer postalCode) {
		super();
		this.id = id;
		this.name = name;
		this.cameBack = cameBack;
		this.available = available;
		this.postalCode = postalCode;
	}

	public Timestamp getCameBack() {
		return cameBack;
	}

	public void setCameBack(Timestamp cameBack) {
		this.cameBack = cameBack;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Rider id: " + id + ", Name: " + name + ", Available: " + available + ", Postal code: " + postalCode ;
	}

}
