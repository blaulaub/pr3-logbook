package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.model.ShiptypeModel
import ch.patchcode.pr3.logbook.objects.Shiptype
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "Shiptype")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("game_id", "name"))))
data class ShiptypeJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val game: GameJpa,
		@Column(nullable = false) val name: String
) {

	fun toDto() = Shiptype(
			id = this.id!!,
			game = this.game.toDto(),
			name = this.name
	)

	fun toModel() = ShiptypeModel(
			id = this.id!!,
			name = this.name
	)
}