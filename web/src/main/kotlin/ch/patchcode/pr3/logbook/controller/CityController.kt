package ch.patchcode.pr3.logbook.controller

import ch.patchcode.pr3.logbook.entities.CityJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.objects.City
import ch.patchcode.pr3.logbook.repositories.CityRepository
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
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

	@PostMapping("/games/{gameId}/cities")
	fun createCity(
			@PathVariable gameId: Long,
			@RequestParam name: String
	): City {
		val game = gameRepository.findById(gameId).get()
		return cityRepository.save(CityJpa(game = game, name = name)).toDto()
	}

	@GetMapping("/games/{gameId}/cities/{cityId}")
	fun getCity(
			@PathVariable gameId: Long,
			@PathVariable cityId: Long
	): City {
		val city = cityRepository.findById(cityId).get()
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)
		return city.toDto()
	}
}