package ch.patchcode.pr3.logbook.cities

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityService @Autowired constructor(
		private val cityRepository: CityRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = cityRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	@Transactional
	fun createCity(gameId: Long, name: String) = cityRepository.save(CityJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@Transactional
	fun getCity(gameId: Long, cityId: Long): CityModel {
		gameService.resolveGame(gameId)
		val city = resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)
		return city.toModel()
	}

	@Transactional
	fun deleteCity(gameId: Long, cityId: Long) {
		val game = gameService.resolveGame(gameId)
		cityRepository.deleteByGameAndId(game, cityId)
	}

	fun resolveCity(cityId: Long): CityJpa {
		val city = cityRepository.findById(cityId)
		if (!city.isPresent) throw EntityNotFoundException("City #" + cityId)
		return city.get()
	}

}