package ch.patchcode.pr3.logbook.games

import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<GameJpa, Long>