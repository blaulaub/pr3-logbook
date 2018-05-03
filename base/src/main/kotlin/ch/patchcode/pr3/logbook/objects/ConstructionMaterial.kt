package ch.patchcode.pr3.logbook.objects

data class ConstructionMaterial(
		val facility: Facility,
		val good: Good,
		val amount: Int
)