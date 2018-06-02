package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.model.FacilityModel
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "Facility")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("game_id", "name"))))
data class FacilityJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore val game: GameJpa,
		@Column(nullable = false) var name: String,
		@Column var constructionCost: Int? = null,
		@Column var constructionDays: Int? = null,
		@Column var maintenancePerDay: Int? = null,
		@Column var workers: Int? = null,
		@OneToMany(mappedBy = "facility", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) val consumption: MutableList<ConsumptionJpa> = ArrayList(),
		@OneToOne(mappedBy = "facility", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var production: ProductionJpa? = null
) {

	fun toModel() = FacilityModel(
			id = this.id!!,
			name = this.name,
			constructionCost = this.constructionCost,
			constructionDays = this.constructionDays,
			maintenancePerDay = this.maintenancePerDay,
			workers = this.workers,
			consumption = this.consumption.map{ it -> it.toModel() }.toList(),
			production = this.production?.toModel()
	)
}