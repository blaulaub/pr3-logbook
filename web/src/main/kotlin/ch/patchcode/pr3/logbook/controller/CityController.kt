package ch.patchcode.pr3.logbook.controller

import ch.patchcode.pr3.logbook.entities.CityJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.objects.City
import ch.patchcode.pr3.logbook.repositories.CityRepository
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController @Autowired constructor(
		private val gameRepository: GameRepository,
		private val cityRepository: CityRepository
) {

	@GetMapping("/games/{gameId}/cities")
	@Transactional
	fun getCities(
			@PathVariable gameId: Long
	) = cityRepository.findByGame(resolveGame(gameId))

	@PostMapping("/games/{gameId}/cities")
	@Transactional
	fun createCity(
			@PathVariable gameId: Long,
			@RequestParam name: String
	) = cityRepository.save(CityJpa(game = resolveGame(gameId), name = name)).toDto()

	@GetMapping("/games/{gameId}/cities/{cityId}")
	@Transactional
	fun getCity(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): City {
		resolveGame(gameId)
		val city = cityRepository.findById(cityId).get()
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)
		return city.toDto()
	}

	private fun resolveGame(gameId: Long): GameJpa {
		val game = gameRepository.findById(gameId)
		if (!game.isPresent) throw EntityNotFoundException("Game #" + gameId)
		return game.get()
	}
}