package ch.patchcode.pr3.logbook.services

import ch.patchcode.pr3.logbook.repositories.FacilityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ch.patchcode.pr3.logbook.entities.FacilityJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.model.FacilityModel
import ch.patchcode.pr3.logbook.entities.TurnoverJpa

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

	fun updateFacility(gameId: Long, facilityId: Long, facility: FacilityModel): FacilityModel {
		if (facilityId != facility.id) throw IllegalArgumentException("URL facilityId does not match model facility id")
		gameService.resolveGame(gameId)
	  val oldFacility = resolveFacility(facility.id);
		if (oldFacility.game.id != gameId) throw EntityNotFoundException("Facility #" + facility.id)

		// before anything else, we have to dive into production and consumption entities
		// if they go fine, we may save-return a new FacilityJpa (see ShiptypeService.updateShiptype)
		if (oldFacility.consumption.isNotEmpty() || oldFacility.production != null) {
			throw RuntimeException("not implemented")
		}
		if (facility.consumption.isNotEmpty() || facility.production != null) {
			throw RuntimeException("not implemented")
		}

		val tempConsumption: List<TurnoverJpa> = ArrayList()
		val tempProduction: TurnoverJpa? = null

		return facilityRepository.save(FacilityJpa(
				id = facility.id,
				game = oldFacility.game,
				name = facility.name,
				constructionCost = facility.constructionCost,
				constructionDays = facility.constructionDays,
				maintenancePerDay = facility.maintenancePerDay,
				workers = facility.workers,
				consumption = tempConsumption,
				production = tempProduction
		)).toModel()
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