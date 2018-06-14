package ch.patchcode.pr3.logbook.routes

import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.fleets.FleetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Collections

@Service
class RouteService @Autowired constructor(
		private val fleetService: FleetService,
		private val cityService: CityService,
		private val routeRepository: RouteRepository) {

	@Transactional
	fun getRoute(gameId: Long, fleetId: Long): RouteModel {
		fleetService.resolveFleet(gameId, fleetId)
		val route = routeRepository.findOneByFleetId(fleetId)
		return route?.toModel() ?: RouteModel(
				travelDays = 0.0,
				routePoints = emptyList()
		)
	}

	@Transactional
	fun updateRoute(gameId: Long, fleetId: Long, route: RouteModel): RouteModel {
		try {
		val fleet = fleetService.resolveFleet(gameId, fleetId)
		val oldRoute = routeRepository.findOneByFleetId(fleetId) ?: RouteJpa(fleet = fleet, travelDays = 0.0)

		val oldPoints: Map<String, MutableList<RoutePointJpa>> = oldRoute.routePoints.groupByTo(mutableMapOf()) { it.city.name }
		// TODO in an elegant way: match/map the existing cities to the old cities.

		var i: Int = 0
		for (point: RoutePointModel in route.routePoints) {

			// ensure there is the right entity in the old route point list
			if (i < oldRoute.routePoints.size) {
				// we can compare the old route point against the new route point
				val oldPoint: RoutePointJpa = oldRoute.routePoints.get(i)
				if (point.city == oldPoint.city.name) {
					tryRemoveFromLookup(oldPoints, point.city)!!
				} else {
					// let's see if there is a match further ahead
					insertMissing(gameId, point.city, oldRoute, i, oldPoints)
				}
			} else {
				// there is only the new route point, so it is different from the old route point
				// let's see if there is a match left
				insertMissing(gameId, point.city, oldRoute, i, oldPoints)
			}

			// now update the route point jpa
			// ... nothing to do yet.
		}


		oldRoute.travelDays = route.travelDays;

		return routeRepository.save(oldRoute).toModel()
		} catch (e: Exception) {
			e.printStackTrace()
			throw e
		}
	}

	fun insertMissing(gameId: Long, cityName: String, oldRoute: RouteJpa, i: Int, oldPoints: Map<String, MutableList<RoutePointJpa>>) {
		val item = tryRemoveFromLookup(oldPoints, cityName)
		if (item != null) {
			oldRoute.routePoints.remove(item)
			oldRoute.routePoints.add(i, item)
		} else {
			oldRoute.routePoints.add(i, RoutePointJpa(
					route = oldRoute,
					position = i,
					city = cityService.resolveByGameAndName(gameId, cityName)
			))
		}
	}

	companion object {
		private fun <K, E> tryRemoveFromLookup(map: Map<K, MutableList<E>>, key: K): E? {
			val x = map.get(key) ?: Collections.emptyList()
			return if (x.isEmpty()) null else x.removeAt(0)
		}

	}

}