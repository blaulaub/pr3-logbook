package ch.patchcode.pr3.logbook.services

import ch.patchcode.pr3.logbook.model.GoodModel
import ch.patchcode.pr3.logbook.repositories.CityProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.entities.CityProductJpa

@Service
class CityProductService @Autowired constructor(
		private val cityProductRepository: CityProductRepository,
		private val gameService: GameService,
		private val cityService: CityService,
		private val goodService: GoodService
) {

	fun findByGameAndCity(gameId: Long, cityId: Long): List<GoodModel> {
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		return cityProductRepository.findByCity(city).map { it -> it.good.toModel() }
	}

	fun updateCityProducts(gameId: Long, cityId: Long, products: List<GoodModel>): List<GoodModel> {
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		val oldGoods = cityProductRepository.findByCity(city).map { it -> it.good }
		val newGoods = products.map { it -> goodService.resolveGood(it.id) }
		for (product in newGoods) {
			if (product.game.id != gameId) throw EntityNotFoundException("Product #" + product.id)
		}

		val obsoleteGoods = oldGoods.minus(newGoods)
		val addedGoods = newGoods.minus(oldGoods)

		obsoleteGoods
				.forEach { it -> cityProductRepository.deleteByCityAndGood(city, it) }
		addedGoods
				.map { it -> CityProductJpa(city = city, good = it) }
				.forEach { it -> cityProductRepository.save(it) }

		return cityProductRepository.findByCity(city).map { it -> it.good.toModel() }
	}
}