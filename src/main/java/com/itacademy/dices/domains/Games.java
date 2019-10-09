package com.itacademy.dices.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Games {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Id
	private int idGame;
	@ElementCollection
	private List <Integer> dices = new ArrayList<Integer>();
	private boolean result;

	public Games() {
	}
	
	public Games(ArrayList <Integer> dices, boolean result) {
		
		this.dices = dices;
		this.result = result;
	}
	
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public List<Integer> getDice1() {
		return dices;
	}

	public void setDice1(List<Integer> dices) {
		this.dices = dices;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "Games [idGame=" + idGame + ", dices=" + dices + ", result=" + result + ", player=" +"]";
	}
	
}