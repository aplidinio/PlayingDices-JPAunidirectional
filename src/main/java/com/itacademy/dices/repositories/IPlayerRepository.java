package com.itacademy.dices.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itacademy.dices.domains.Player;


//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

@Repository
public interface IPlayerRepository extends CrudRepository<Player, Integer>{


}
