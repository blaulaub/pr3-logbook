package ch.patchcode.pr3.logbook.perheadconsumptions

import ch.patchcode.pr3.logbook.goods.GoodJpa
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.persistence.Column

@Entity(name = "PerHeadConsumption")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("good_id"))))
data class PerHeadConsumptionJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val good: GoodJpa,
		@Column(nullable = false) var consumptionPerHundred: Double
) {

	fun toModel() = PerHeadConsumptionModel(
			good = good.name,
			consumptionPerHundred = consumptionPerHundred
	)
}
