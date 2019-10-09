package com.itacademy.dices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itacademy.dices.domains.Games;
import com.itacademy.dices.domains.Player;
import com.itacademy.dices.repositories.IPlayerRepository;

@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(IPlayerRepository playerRepository) {
    return args -> {
      System.out.println("Preloading Data to memory Database");
      Player newPlayer = new Player("Poker De Ases", LocalDate.now(), new ArrayList<Games>(), 100);
      ArrayList <Integer> dices = new ArrayList <Integer>();
      dices.add(2); 
      dices.add(5); 
      Games newGame = new Games(dices, true);
      List <Games> newGameList = new ArrayList <Games>();
      newGameList.add(newGame);
          
      newPlayer.setGames(newGameList);
          
      playerRepository.save(newPlayer);
	
      System.out.println("Data loaded");
      
    };
  }
  
  }

 