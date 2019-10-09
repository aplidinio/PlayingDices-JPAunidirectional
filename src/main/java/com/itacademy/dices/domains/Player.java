package com.itacademy.dices.domains;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {
    
	private String name;
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Id
    private Integer idPlayer;  
	private LocalDate dateRegister;
	@ElementCollection
	@OneToMany (cascade = CascadeType.ALL)
	private List <Games> games = new ArrayList <Games>();
	private int rateSuccess;
	
	public Player() {
		
	}
		
	public Player(String name, LocalDate dateRegister, ArrayList <Games> games, int rateSuccess) {
		
		this.name = name;
		this.dateRegister = LocalDate.now();
		this.games = games;
		this.rateSuccess = rateSuccess;
	}

	public int getRateSuccess() {
		return rateSuccess;
	}

	public void setRateSuccess(int rateSuccess) {
		this.rateSuccess = rateSuccess;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public LocalDate getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDate dateRegister) {
		this.dateRegister = dateRegister;
	}

	public List<Games> getGames() {
		return games;
	}

	public void setGames(List<Games> games) {
		this.games = games;
				
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", idPlayer=" + idPlayer + ", dateRegister=" + dateRegister + ", games=" + games
				+ ", rateSuccess=" + rateSuccess + "]";
	}

	public int calculateSuccess() {
		int success=0;
		for (Games g : games) {
			 if (g.getResult()) success++;
			}
		 			  
		rateSuccess = (int)(success*100/games.size());
		if (games.size()==0) rateSuccess=0;
		return rateSuccess;
		
	}
		
}