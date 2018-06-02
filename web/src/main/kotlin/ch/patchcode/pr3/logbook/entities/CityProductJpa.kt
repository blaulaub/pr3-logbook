package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.goods.GoodJpa
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "CityProduct")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("city_id", "good_id"))))
data class CityProductJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) val city: CityJpa,
		@ManyToOne(optional = false) val good: GoodJpa
)