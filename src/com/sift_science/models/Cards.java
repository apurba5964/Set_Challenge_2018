package com.sift_science.models;

/**
 * Cards.java 
 * Purpose: To create a Card object. 
 * 			
 *
 * @author Apurba Mahanta
 * @version 1.0 
 */

public class Cards {

	private String color;
	private String symbol;
	private String shade;
	private int number;
	private String input;
	
	public Cards() {
		// Default Constructor
	}
	
	
	



	public Cards(String color, String symbol, String shade, int number, String input) {
		super();
		this.color = color;
		this.symbol = symbol;
		this.shade = shade;
		this.number = number;
		this.input = input;
	}






	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getSymbol() {
		return symbol;
	}



	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



	public String getShade() {
		return shade;
	}



	public void setShade(String shade) {
		this.shade = shade;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}

	

	public String getInput() {
		return input;
	}






	public void setInput(String input) {
		this.input = input;
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + number;
		result = prime * result + ((shade == null) ? 0 : shade.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cards other = (Cards) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (number != other.number)
			return false;
		if (shade == null) {
			if (other.shade != null)
				return false;
		} else if (!shade.equals(other.shade))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}






	@Override
	public String toString() {
		return input;	}



	
	
	
	
	
}
