package ch.patchcode.pr3.logbook.routes

import ch.patchcode.pr3.logbook.fleets.FleetJpa
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.persistence.OneToMany
import javax.persistence.CascadeType
import javax.persistence.OneToOne
import ch.patchcode.pr3.logbook.cities.CityJpa

@Entity(name = "Route")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("fleet_id"))))
data class RouteJpa(
		@Id @GeneratedValue val id: Long? = null,
		@OneToOne(optional = false) val fleet: FleetJpa,
		@Column(nullable = false) var travelDays: Double,
		@OneToMany(mappedBy = "route", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) val routePoints: MutableList<RoutePointJpa> = ArrayList()
) {

	fun toModel() = RouteModel(
			travelDays = this.travelDays,
			routePoints = this.routePoints.map { point -> point.toModel() }
	)
}

@Entity(name = "RoutePoint")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("route_id", "position"))))
data class RoutePointJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val route: RouteJpa,
		@Column(nullable = false) var position: Int,
		@ManyToOne(optional = false) var city: CityJpa
) {

	fun toModel() = RoutePointModel(city.name)
}
