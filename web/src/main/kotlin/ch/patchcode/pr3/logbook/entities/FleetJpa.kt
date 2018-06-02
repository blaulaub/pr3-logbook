package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.model.FleetModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "Fleet")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("game_id", "name"))))
data class FleetJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val game: GameJpa,
		@Column(nullable = false) val name: String
) {

	fun toModel() = FleetModel(
			id = this.id!!,
			name = this.name
	)
}