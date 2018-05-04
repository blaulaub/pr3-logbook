package ch.patchcode.pr3.logbook.repositories

import org.springframework.data.repository.CrudRepository
import ch.patchcode.pr3.logbook.entities.GameJpa

interface GameRepository : CrudRepository<GameJpa, Long>