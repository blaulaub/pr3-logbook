package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.model.GoodModel
import ch.patchcode.pr3.logbook.objects.Good
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity(name = "Good")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("game_id", "name"))))
data class GoodJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore val game: GameJpa,
		@Column(nullable = false) val name: String
) {

	fun toDto() = Good(
			id = this.id!!,
			game = this.game.toDto(),
			name = this.name
	)

	fun toModel() = GoodModel(
			id = this.id!!,
			name = this.name
	)
}