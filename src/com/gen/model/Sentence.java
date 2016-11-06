package com.gen.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sentence {
	
	public static int count = 1;
	private final StringProperty sentence;
	private final IntegerProperty index;
	
	public Sentence(String sentence) {
		this.sentence = new SimpleStringProperty(sentence);
		this.index = new SimpleIntegerProperty(count);
		count++;
	}
	
	public Sentence() {
		this.sentence = new SimpleStringProperty("");
		this.index = new SimpleIntegerProperty(count);
		count++;
	}
	
	public IntegerProperty index() {
		return index;
	}
	
	public StringProperty sentence() {
		return sentence;
	}
	
	public String getSentence() {
		return sentence.get();
	}
	
	public int getIndex() {
		return index.get();
	}
	
	public void setSentence(String sentence) {
		this.sentence.set(sentence);
	}
	
	public void setIndex(int index) {
		this.index.set(index);
	}

}
