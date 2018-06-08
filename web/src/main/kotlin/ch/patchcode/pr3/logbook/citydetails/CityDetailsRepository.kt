package ch.patchcode.pr3.logbook.citydetails

import org.springframework.data.repository.CrudRepository
import ch.patchcode.pr3.logbook.cities.CityJpa

interface CityDetailsRepository : CrudRepository<CityDetailsJpa, Long> {
	fun findOneByCity(city: CityJpa) : CityDetailsJpa?
}