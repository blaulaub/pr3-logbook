package ch.patchcode.pr3.logbook.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * The anchor entity for any game data.
 */
@Entity
data class Game(
		@Id @GeneratedValue val id: Long? = null,
		var captainsName: String
)