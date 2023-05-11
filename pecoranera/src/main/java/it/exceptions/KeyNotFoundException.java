package it.exceptions;

public class KeyNotFoundException extends Exception {
	
	public KeyNotFoundException() {
		super("Entered key does not exist");
	}
}
