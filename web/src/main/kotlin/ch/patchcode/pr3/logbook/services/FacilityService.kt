package ch.patchcode.pr3.logbook.services

import ch.patchcode.pr3.logbook.repositories.FacilityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ch.patchcode.pr3.logbook.entities.FacilityJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.model.FacilityModel

@Service
class FacilityService @Autowired constructor(
		private val facilityRepository: FacilityRepository,
		private val gameService: GameService
) {

	fun findByGame(gameId: Long) = facilityRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	fun createFacility(gameId: Long, name: String) = facilityRepository.save(FacilityJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	fun getFacility(gameId: Long, facilityId: Long): FacilityModel {
		gameService.resolveGame(gameId)
		val facility = resolveFacility(facilityId)
		if (facility.game.id != gameId) throw EntityNotFoundException("Facility #" + facilityId)
		return facility.toModel()
	}

	fun deleteFacility(gameId: Long, facilityId: Long) {
		val game = gameService.resolveGame(gameId)
		facilityRepository.deleteByGameAndId(game, facilityId)
	}

	private fun resolveFacility(facilityId: Long): FacilityJpa {
		val facility = facilityRepository.findById(facilityId)
		if (!facility.isPresent) throw EntityNotFoundException("Facility #" + facilityId)
		return facility.get()
	}

}