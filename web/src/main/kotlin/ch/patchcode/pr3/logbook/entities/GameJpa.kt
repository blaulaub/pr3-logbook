package ch.patchcode.pr3.logbook.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import java.time.LocalDate
import java.time.LocalDateTime
import ch.patchcode.pr3.logbook.objects.Game

/**
 * The anchor entity for any game data.
 */
@Entity(name = "Game")
data class GameJpa(
		@Id @GeneratedValue val id: Long? = null,
		val captainsName: String,
		val created: LocalDateTime = LocalDateTime.now()
) {

	fun toDto() = Game(
			id = this.id!!,
			captainsName = this.captainsName,
			created = this.created
	)

	companion object {

		fun fromDto(dto: Game) = GameJpa(
				id = dto.id,
				captainsName = dto.captainsName,
				created = dto.created
		)
	}
}