package ch.patchcode.pr3.logbook.routes

import org.springframework.data.jpa.repository.JpaRepository

interface RouteRepository : JpaRepository<RouteJpa, Long> {
	fun findOneByFleetId(fleetId: Long): RouteJpa?
}