package ch.patchcode.pr3.logbook.cityFactories

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CityFactoryController @Autowired constructor(
		private val cityFactoryService: CityFactoryService
) {

	companion object {
		val log = LoggerFactory.getLogger(CityFactoryController::class.java)
	}

	@GetMapping("/games/{gameId}/cities/{cityId}/factoryCounts")
	fun getCityProducts(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): List<FactoryCountModel> {
		return cityFactoryService.findByGameAndCity(gameId, cityId)
	}

	@PutMapping("/games/{gameId}/cities/{cityId}/factoryCounts")
	fun updateCityProducts(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long,
			@RequestBody counts: List<FactoryCountModel>
	): List<FactoryCountModel> = cityFactoryService.updateFactoryCounts(gameId, cityId, counts)
}
