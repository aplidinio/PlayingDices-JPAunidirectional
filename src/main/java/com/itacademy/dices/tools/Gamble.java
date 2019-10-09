package com.itacademy.dices.tools;

import java.util.ArrayList;

public class Gamble {
			
	private ArrayList <Integer> dices = new ArrayList <Integer> ();
	
	public ArrayList<Integer> throwDices(int numberOfDices) {
				
		for (int i=0; i<numberOfDices; i++) {
			
			dices.add((Integer)(int)(Math.random()*6)+1);
		}
					
		return dices;
	}
	
	public boolean winOrFailTwoDices() {
		
		byte counter=0;
				
		for (Integer e : dices) {
			
			counter+=e.intValue();				
		}		
		if (counter==7)
			return true;
		else				
			return false;
	}
	
	public boolean winOrFailSixDices() {
		
		byte counterFive=0;
		byte counterSix=0;
		
		for (Integer e : dices) {
			
			if (e==5) counterFive++;
			if (e==6) counterSix++;
		}
		
		if (counterFive==6 || counterSix==6)
			return true;
		else
			return false;
			
	}

}