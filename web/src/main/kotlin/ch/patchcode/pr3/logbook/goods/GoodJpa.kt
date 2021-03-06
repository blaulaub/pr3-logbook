package ch.patchcode.pr3.logbook.goods

import ch.patchcode.pr3.logbook.games.GameJpa
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "Good")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("game_id", "name"))))
data class GoodJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore val game: GameJpa,
		@Column(nullable = false) val name: String
) {

	fun toModel() = GoodModel(
			id = this.id!!,
			name = this.name
	)
}