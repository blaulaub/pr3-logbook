package ch.patchcode.pr3.logbook.citydetails

import ch.patchcode.pr3.logbook.cities.CityJpa
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "CityDetails")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("city_id"))))
data class CityDetailsJpa(
		@Id @GeneratedValue val id: Long? = null,
		@OneToOne(optional = false) val city: CityJpa,
		@Column(nullable = false) var population: Int,
		@Column(nullable = false) var warehouses: Int,
		@Column(nullable = false) var support: Double,
		@Column(nullable = false) var isExportCity: Boolean		
) {

	fun toModel() = CityDetailsModel(
			population = this.population,
			warehouses = this.warehouses,
			support = this.support,
			isExportCity = this.isExportCity
	)
}