package ch.patchcode.pr3.logbook.cityproducts

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.goods.GoodJpa
import org.springframework.data.repository.CrudRepository

interface CityProductRepository : CrudRepository<CityProductJpa, Long> {
	fun findByCity(city: CityJpa): Iterable<CityProductJpa>
	fun findByGood(good: GoodJpa): Iterable<CityProductJpa>
	fun deleteByCityAndGood(city: CityJpa, good: GoodJpa)
}
