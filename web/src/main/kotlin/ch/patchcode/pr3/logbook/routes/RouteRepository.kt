package ch.patchcode.pr3.logbook.routes

import org.springframework.data.repository.CrudRepository

interface RouteRepository : CrudRepository<RouteJpa, Long> {
	fun findOneByFleetId(fleetId: Long): RouteJpa?
}