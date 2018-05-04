package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.objects.Game
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * The anchor entity for any game data.
 */
@Entity(name = "Game")
data class GameJpa(
		@Id @GeneratedValue val id: Long? = null,
		@Column(nullable = false) val captainsName: String,
		@Column(nullable = false) val created: LocalDateTime = LocalDateTime.now()
) {

	fun toDto() = Game(
			id = this.id!!,
			captainsName = this.captainsName,
			created = this.created
	)
}