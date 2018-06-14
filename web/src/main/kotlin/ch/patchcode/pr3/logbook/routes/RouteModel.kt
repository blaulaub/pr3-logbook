package ch.patchcode.pr3.logbook.routes

data class RouteModel (
		val travelDays: Double,
		val routePoints: List<RoutePointModel>
)

data class RoutePointModel (
		val city: String
)