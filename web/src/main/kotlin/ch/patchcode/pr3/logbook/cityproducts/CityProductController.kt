package ch.patchcode.pr3.logbook.cityproducts

import ch.patchcode.pr3.logbook.cities.CityModel
import ch.patchcode.pr3.logbook.goods.GoodModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CityProductController @Autowired constructor(
		private val cityProductService: CityProductService
) {

	@GetMapping("/games/{gameId}/cities/{cityId}/products")
	fun getCityProducts(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): List<GoodModel> = cityProductService.findByGameAndCity(gameId, cityId)

	@PutMapping("/games/{gameId}/cities/{cityId}/products")
	fun updateCityProducts(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long,
			@RequestBody products: List<GoodModel>
	): List<GoodModel> = cityProductService.updateCityProducts(gameId, cityId, products)

	@GetMapping("/games/{gameId}/goods/{goodId}/producingCities")
	fun getProducedIn(
			@PathVariable gameId: Long,
			@PathVariable goodId: Long
	): List<CityModel> = cityProductService.findCitiesProducing(gameId, goodId)
}
