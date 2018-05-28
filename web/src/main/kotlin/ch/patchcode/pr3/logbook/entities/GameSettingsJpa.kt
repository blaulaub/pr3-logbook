package ch.patchcode.pr3.logbook.entities

import ch.patchcode.pr3.logbook.model.GameSettingsModel
import ch.patchcode.pr3.logbook.objects.GameSettings
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity(name = "GameSettings")
@Table(uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("game_id"))))
data class GameSettingsJpa(
		@Id @GeneratedValue val id: Long? = null,
		@OneToOne(optional = false) val game: GameJpa,
		@Column var salaryPerDay: Double? = null,
		@Column var workerPerCitizenRatio: Double? = null
) {

	fun toDto() = GameSettings(
			game = this.game.toDto(),
			salaryPerDay = this.salaryPerDay,
			workerPerCitizenRatio = this.workerPerCitizenRatio
	)

	fun toModel() = GameSettingsModel(
			salaryPerDay = this.salaryPerDay,
			workerPerCitizenRatio = this.workerPerCitizenRatio
	)
}