package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import ch.patchcode.pr3.logbook.goods.GoodJpa
import ch.patchcode.pr3.logbook.goods.GoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FacilityService @Autowired constructor(
		private val facilityRepository: FacilityRepository,
		private val goodRepository: GoodRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = facilityRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

	@Transactional
	fun createFacility(gameId: Long, name: String) = facilityRepository.save(FacilityJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@Transactional
	fun getFacility(gameId: Long, facilityId: Long): FacilityModel {
		gameService.resolveGame(gameId)
		val facility = resolveFacility(facilityId)
		if (facility.game.id != gameId) throw EntityNotFoundException("Facility #" + facilityId)
		return facility.toModel()
	}

	@Transactional
	fun updateFacility(gameId: Long, facilityId: Long, facility: FacilityModel): FacilityModel {
		if (facilityId != facility.id) throw IllegalArgumentException("URL facilityId does not match model facility id")
		val game = gameService.resolveGame(gameId)
		val oldFacility = resolveFacility(facility.id);
		if (oldFacility.game.id != gameId) throw EntityNotFoundException("Facility #" + facility.id)

		oldFacility.name = facility.name
		oldFacility.constructionCost = facility.constructionCost
		oldFacility.constructionDays = facility.constructionDays
		oldFacility.maintenancePerDay = facility.maintenancePerDay
		oldFacility.workers = facility.workers

		val oldConsumptions = oldFacility.consumption.associateBy({ item -> item.good.name }, { item -> item })
		val newConsumptions = facility.consumption.associateBy({ item -> item.good.name }, { item -> item })

		val obsolete = oldConsumptions.keys.minus(newConsumptions.keys)

		oldFacility.consumption.removeAll { item -> obsolete.contains(item.good.name) }
		for (item in newConsumptions.values) {
			var itemJpa = oldConsumptions.get(item.good.name)
			if (itemJpa == null) {
				val good = goodRepository.findOneByGameAndName(game, item.good.name)!!
				itemJpa = ConsumptionJpa(
						facility = oldFacility,
						good = good)
				oldFacility.consumption.add(itemJpa)
			}
			itemJpa.amount = item.amount
		}

		if (facility.production == null) {
			oldFacility.production = null
		} else {
			var itemJpa = oldFacility.production
			if (itemJpa == null || itemJpa.good.name != facility.production.good.name) {
				itemJpa = ProductionJpa(
						facility = oldFacility,
						good = goodRepository.findOneByGameAndName(game, facility.production.good.name)!!)
				oldFacility.production = itemJpa
			}
			itemJpa.amount = facility.production.amount
		}

		return facilityRepository.save(oldFacility).toModel()
	}

	@Transactional
	fun deleteFacility(gameId: Long, facilityId: Long) {
		val game = gameService.resolveGame(gameId)
		facilityRepository.deleteByGameAndId(game, facilityId)
	}

	fun deleteByGameId(gameId: Long) = facilityRepository.deleteByGameId(gameId)

	fun resolveByProduct(product: GoodJpa) = facilityRepository.findOneByProductionGood(product)

	fun resolveFacility(facilityId: Long): FacilityJpa {
		val facility = facilityRepository.findById(facilityId)
		if (!facility.isPresent) throw EntityNotFoundException("Facility #" + facilityId)
		return facility.get()
	}
}
