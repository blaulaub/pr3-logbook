package ch.patchcode.pr3.logbook

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Greeting(
		@Id @GeneratedValue var id: Long? = null,
		var content: String
)