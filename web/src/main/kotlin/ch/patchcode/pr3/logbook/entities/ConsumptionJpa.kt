package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.model.ConsumptionModel
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "Consumption")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("facility_id", "good_id"))))
data class ConsumptionJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore val facility: FacilityJpa,
		@ManyToOne(optional = false) val good: GoodJpa,
		@Column(nullable = false) var amount: Double = 0.0
) {

	fun toModel() = ConsumptionModel(
			good = this.good.toModel(),
			amount = this.amount
	)
}