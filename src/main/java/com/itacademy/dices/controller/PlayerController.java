package com.itacademy.dices.controller;

import java.time.LocalDate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.dices.domains.Games;
import com.itacademy.dices.domains.Player;
import com.itacademy.dices.exceptions.*;
import com.itacademy.dices.repositories.IPlayerRepository;
import com.itacademy.dices.tools.Gamble;

@RestController
@RequestMapping("/dices")
public class PlayerController{
	
	private final IPlayerRepository repository;

	  @Autowired
	  public PlayerController(IPlayerRepository repository) {
	    this.repository = repository;
	  }
	  	 	
	  //Get all players and their success rate    FUNCIONA
	  @GetMapping("/players")
	  public Iterable <Player> getAllPlayers() {
		  return repository.findAll();
	  }
	  	 	  
	  //Create a new player				FUNCIONA
	  @PostMapping("/players")
	  public Player newPlayer(@RequestBody Player newPlayer) {
		  newPlayer.setDateRegister(LocalDate.now());
		  if (newPlayer.getName()=="") newPlayer.setName("Anonymous");
		  return repository.save(newPlayer);
	  }
	  
	  //Get a player						FUNCIONA
	  @GetMapping("/players/{idPlayer}")
	  public Player getPlayer(@PathVariable Integer idPlayer) {
		return repository.findById(idPlayer)
				.orElseThrow(() -> new PlayerNotFoundException(Integer.toString(idPlayer)));                
	  }
	  
	  //Delete a player                                FUNCIONA
	  @DeleteMapping("/players/{idPlayer}")
	  public void deletePlayer(@PathVariable Integer idPlayer) {
		 repository.deleteById(idPlayer);
	  }
	  
	  //Modify a player                    FUNCIONA
	  @PutMapping("/players/{idPlayer}")
	  public Player modifyPlayer(@RequestBody Player modifyPlayer, @PathVariable Integer idPlayer) {
		  return repository.findById(idPlayer)
				  .map(player -> {
					  player.setName(modifyPlayer.getName());
					  return repository.save(player);
					})
				  .orElseGet(() -> {
				        modifyPlayer.setIdPlayer(idPlayer);
				        return repository.save(modifyPlayer);
				    });
		  
	  }
	  
	  //Player makes a 2 dices gamble						FUNCIONA
	  @PostMapping("/players/{idPlayer}/games/twodices")
	  public Player gambleTwoPlayer(@PathVariable Integer idPlayer) {
		  Gamble gamble = new Gamble();
		  Games newGame = new Games(gamble.throwDices(2), gamble.winOrFailTwoDices());
		  
		  return repository.findById(idPlayer)
				  .map(player -> {
					  player.getGames().add(newGame);
					  player.calculateSuccess();
					  return repository.save(player);
		  })
				  .orElseThrow(() -> {
				        return new PlayerNotFoundException(Integer.toString(idPlayer));
		  });
		  
	  }
	  
	//Player makes a 6 dices gamble						
	  @PostMapping("/players/{idPlayer}/games/sixdices")
	  public Player gambleSixPlayer(@PathVariable Integer idPlayer) {
		  Gamble gamble = new Gamble();
		  Games newGame = new Games(gamble.throwDices(6), gamble.winOrFailSixDices());
		  
		  return repository.findById(idPlayer)
				  .map(player -> {
					  player.getGames().add(newGame);
					  player.calculateSuccess();
					  return repository.save(player);
		  })
				  .orElseThrow(() -> {
				        return new PlayerNotFoundException(Integer.toString(idPlayer));
		  });
		  
	  }
	  
	  //Delete player games								FUNCIONA
	  @DeleteMapping("/players/{idPlayer}/games")
	  public void deleteAllGames(@PathVariable Integer idPlayer) {
		  repository.findById(idPlayer)
		  	.map(player ->{
		  		player.getGames().clear();
		  		return repository.save(player);
			  
		  })
		  	.orElseThrow(() -> {
		  		return new PlayerNotFoundException(Integer.toString(idPlayer));		  		
		  });
	  }
	  
	  //Get all games from a specified player
	  @GetMapping("/players/{idPlayer}/games")
	  public Iterable<Games> getAllGames(@PathVariable Integer idPlayer) {
		  return repository.findById(idPlayer)
		  	.map(player ->{
		  		player.getGames();
		  		return repository.findById(idPlayer).get().getGames();
		  				  		
		  	})
		  	.orElseThrow(() -> {
		  		return new PlayerNotFoundException(Integer.toString(idPlayer));		  		
		  });
	  }
	  
	  //Get success average from all players   			FUNCIONA
	  @GetMapping("/players/ranking")
	  public int getSuccessAverage(){
		
		  int totalSumSuccess = 0;
		  int counter=0;
		  
		  for (Player e : repository.findAll()) {
			  totalSumSuccess += e.getRateSuccess();
			  counter++;
		  }
		  return (int)(totalSumSuccess/counter);
	  }
			  
	  //Get player with highest success rate
	  @GetMapping("/players/ranking/winner")
	  public Player getTopPlayer() {
		  Collection <Player> players = (Collection<Player>) getAllPlayers();
		  
		  Player topPlayer = Collections.max(players, Comparator.comparing(Player::getRateSuccess));
		  		  	  
		  return repository.findById(topPlayer.getIdPlayer())
				  .orElseThrow(() -> new PlayerNotFoundException("Fatal error"));
		  			
	  }
	  
	  //Get player with lowest success rate
	  @GetMapping("/players/ranking/loser")
	  public Player getBottomPlayer() {
		  Collection <Player> players = (Collection<Player>) getAllPlayers();
		  
		  Player bottomPlayer = Collections.min(players, Comparator.comparing(Player::getRateSuccess));
		  
		  return repository.findById(bottomPlayer.getIdPlayer())
				  .orElseThrow(() -> new PlayerNotFoundException("Fatal error"));
	  }
	  
}









