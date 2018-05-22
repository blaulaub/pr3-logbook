package ch.patchcode.pr3.logbook.entities

import javax.persistence.ManyToOne
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.UniqueConstraint
import ch.patchcode.pr3.logbook.model.ProductionModel
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity(name = "Consumption")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("facility_id", "good_id"))))
data class ProductionJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore val facility: FacilityJpa,
		@ManyToOne(optional = false) val good: GoodJpa,
		@Column(nullable = false) var amount: Double = 0.0
) {

	fun toModel() = ProductionModel(
			good = this.good.toModel(),
			amount = this.amount
	)
}