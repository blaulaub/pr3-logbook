package ch.patchcode.pr3.logbook.cityproducts

import ch.patchcode.pr3.logbook.cities.CityModel
import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodModel
import ch.patchcode.pr3.logbook.goods.GoodService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CityProductService @Autowired constructor(
		private val cityProductRepository: CityProductRepository,
		private val gameService: GameService,
		private val cityService: CityService,
		private val goodService: GoodService
) {

	fun findByGameAndCity(gameId: Long, cityId: Long): List<GoodModel> = resolveByGameAndCity(gameId, cityId).map { it -> it.good.toModel() }

	fun resolveByGameAndCity(gameId: Long, cityId: Long): Iterable<CityProductJpa> {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		return cityProductRepository.findByCity(city)
	}

	fun updateCityProducts(gameId: Long, cityId: Long, products: List<GoodModel>): List<GoodModel> {
		gameService.resolveGame(gameId)
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

	fun findCitiesProducing(gameId: Long, goodId: Long): List<CityModel> {
		gameService.resolveGame(gameId)
		val good = goodService.resolveGood(goodId)
		if (good.game.id != gameId) throw EntityNotFoundException("Good #" + goodId)
		return cityProductRepository.findByGood(good).map { it -> it.city.toModel() };
	}
}