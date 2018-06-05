package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.goods.GoodJpa
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "BillOfMaterial")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(
		columnNames = arrayOf("facility_id", "good_id"))))
data class BillOfMaterialJpa(
		@Id @GeneratedValue val id: Long? = null,
		@ManyToOne(optional = false) @JsonIgnore override val facility: FacilityJpa,
		@ManyToOne(optional = false) override val good: GoodJpa,
		@Column(nullable = false) override var amount: Double = 0.0
) : Turnover