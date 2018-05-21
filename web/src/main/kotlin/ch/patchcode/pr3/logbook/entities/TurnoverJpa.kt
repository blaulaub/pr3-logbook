package ch.patchcode.pr3.logbook.entities

import javax.persistence.ManyToOne
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import ch.patchcode.pr3.logbook.model.Turnover
import javax.persistence.UniqueConstraint

@Entity(name = "Turnover")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("facility_id", "good_id"))))
data class TurnoverJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val facility: FacilityJpa,
		@ManyToOne(optional = false) val good: GoodJpa,
		@Column(nullable = false) val amount: Double
) {

	fun toModel() = Turnover(
			id = this.id!!,
			good = this.good.toModel(),
			amount = this.amount
	)
}