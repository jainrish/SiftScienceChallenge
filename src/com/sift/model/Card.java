package com.sift.model;

public class Card {

	private int color;
	private int symbol;
	private int shade;
	private int number;
	private String cardID;
	private String colorStr;
	private String attributes;
	
	public int getColor() {
		return color;
	}

	public int getSymbol() {
		return symbol;
	}

	public int getShade() {
		return shade;
	}

	public int getNumber() {
		return number;
	}

	public String getCardID() {
		return cardID;
	}
	
	public Card(String color, String attributes) {
		this.color = getColor(color);
		this.symbol = getSymbol(attributes);
		this.shade = getShade(attributes);
		this.number = attributes.length()==3?4:attributes.length();
		this.cardID = String.valueOf((((this.color*10)+this.symbol)*10+this.shade)*10+this.number);
		this.colorStr = color;
		this.attributes = attributes;
	}
	
	public boolean isSet(Card card1, Card card2) {
		 return ((this.color==card1.color&&this.color==card2.color)||(this.color!=card1.color&&card1.color!=card2.color&&this.color!=card2.color)) &&
				 ((this.symbol==card1.symbol&&this.symbol==card2.symbol)||(this.symbol!=card1.symbol&&card1.symbol!=card2.symbol&&this.symbol!=card2.symbol)) &&
				 ((this.shade==card1.shade&&this.shade==card2.shade)||(this.shade!=card1.shade&&card1.shade!=card2.shade&&this.shade!=card2.shade)) &&
				 ((this.number==card1.number&&this.number==card2.number)||(this.number!=card1.number&&card1.number!=card2.number&&this.number!=card2.number));
	}
	
	public String getThirdCard(Card card) {
		int cardColor = this.color==card.color?card.color:(7-this.color-card.color);
		int cardSymbol = this.symbol==card.symbol?card.symbol:(7-this.symbol-card.symbol);
		int cardShade = this.shade==card.shade?card.shade:(7-this.shade-card.shade);
		int cardNumber = this.number==card.number?card.number:(7-this.number-card.number);
		return String.valueOf((((cardColor*10)+cardSymbol)*10+cardShade)*10+cardNumber);
	}
	
	
	private int getColor(String color) {
		return "blue".equals(color)?1:"green".equals(color)?2:4;
	}
	
	private int getSymbol(String attribute) {
		if(attribute.contains("a") || attribute.contains("A") || attribute.contains("@")) {
			return 1;
		} else if(attribute.contains("s") || attribute.contains("S") || attribute.contains("$")) {
			return 2;
		} else {
			return 4;
		}
	}
	
	private int getShade(String attribute) {
		if(attribute.contains("a") || attribute.contains("s") || attribute.contains("h")) {
			return 1;
		} else if(attribute.contains("A") || attribute.contains("S") || attribute.contains("H")) {
			return 2;
		} else {
			return 4;
		}
	}
	
	@Override
	public String toString() {
		return colorStr.concat(" ").concat(attributes);
	}
}
