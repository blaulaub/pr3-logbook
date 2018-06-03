package ch.patchcode.pr3.logbook.cityFactories

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.facilities.FacilityJpa
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "FactoryCount")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("city_id", "facility_id"))))
data class FactoryCountJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val city: CityJpa,
		@ManyToOne(optional = false) val facility: FacilityJpa,
		@Column(nullable = false) var rivalCount: Int = 0,
		@Column(nullable = false) var playerCount: Int = 0
) {

	fun toModel() = FactoryCountModel(
			facilityId = this.facility.id!!,
			facilityName = this.facility.name,
			rivalCount = this.rivalCount,
			playerCount = this.playerCount
	)
}
