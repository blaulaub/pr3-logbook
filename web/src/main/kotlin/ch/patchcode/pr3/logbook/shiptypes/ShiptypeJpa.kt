package ch.patchcode.pr3.logbook.shiptypes

import ch.patchcode.pr3.logbook.games.GameJpa
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
		@Column(nullable = false) val name: String,
		@Column val cargoSpace: Int? = null,
		@Column val maneuverability: Int? = null,
		@Column val draft: Int? = null,
		@Column val minSpeed: Int? = null,
		@Column val maxSpeed: Int? = null,
		@Column val cannons: Int? = null,
		@Column val sailors: Int? = null,
		@Column val hitPoints: Int? = null,
		@Column val dailyCost: Int? = null,
		@Column val price: Int? = null

) {

	fun toModel() = ShiptypeModel(
			id = this.id!!,
			name = this.name,
			cargoSpace = this.cargoSpace,
			maneuverability = this.maneuverability,
			draft = this.draft,
			minSpeed = this.minSpeed,
			maxSpeed = this.maxSpeed,
			cannons = this.cannons,
			sailors = this.sailors,
			hitPoints = this.hitPoints,
			dailyCost = this.dailyCost,
			price = this.price
	)
}