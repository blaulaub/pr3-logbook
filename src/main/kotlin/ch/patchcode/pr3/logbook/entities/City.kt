package ch.patchcode.pr3.logbook.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class City (
		@Id @GeneratedValue val id: Long? = null,
		val name: String
)