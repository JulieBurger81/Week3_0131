/**
 * @author Julie Burger - jaburger
 * CIS175 - Spring 2023
 * Jan 21, 2023
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="games")
public class Games {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int Id;
	@Column(name="NAME")
	private String name;
	@Column(name="Type")
	private String type;
	@Column(name="NUM_OF_PLAYERS")
	private int numOfPlayers;
	/**
	 * 
	 */
	public Games() {
	}
	/**
	 * @param name
	 * @param type
	 * @param numOfPlayers
	 */
	public Games(String name, String type, int numOfPlayers) {
		setName(name);
		setType(type);
		setNumOfPlayers(numOfPlayers);
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the numOfPlayers
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	/**
	 * @param numOfPlayers the numOfPlayers to set
	 */
	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	@Override
	public String toString() {
		return "Games [Id=" + Id + ", name=" + name + ", type=" + type + ", numOfPlayers=" + numOfPlayers + "]";
	}
	
	public String returnGameDetails() {
		return this.name + ", a " + this.type + " game, with " + this.numOfPlayers + " players.";
	}
	
}
