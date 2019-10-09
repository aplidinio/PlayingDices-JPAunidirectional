package com.itacademy.dices.exceptions;

public class PlayerNotFoundException extends RuntimeException {
	
	public PlayerNotFoundException (String name) {
		super("The player " + name + "has not been found in the database.");
	}

}
