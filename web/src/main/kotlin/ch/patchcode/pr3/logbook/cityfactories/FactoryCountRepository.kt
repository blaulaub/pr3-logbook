package ch.patchcode.pr3.logbook.cityFactories

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.facilities.FacilityJpa
import org.springframework.data.repository.CrudRepository

interface FactoryCountRepository : CrudRepository<FactoryCountJpa, Long> {
	fun findByCity(city: CityJpa): Iterable<FactoryCountJpa>
	fun deleteByCityAndFacility(city: CityJpa, facility: FacilityJpa)
}