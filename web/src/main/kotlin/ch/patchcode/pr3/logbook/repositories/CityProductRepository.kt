package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.entities.CityProductJpa
import ch.patchcode.pr3.logbook.entities.GoodJpa
import org.springframework.data.repository.CrudRepository

interface CityProductRepository: CrudRepository<CityProductJpa, Long> {
	fun findByCity(city: CityJpa): Iterable<CityProductJpa>
	fun findByGood(good: GoodJpa): Iterable<CityProductJpa>
	fun deleteByCityAndGood(city: CityJpa, good: GoodJpa)
}
