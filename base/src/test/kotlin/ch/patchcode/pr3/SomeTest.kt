package ch.patchcode.pr3

import ch.patchcode.pr3.logbook.objects.Game
import org.junit.Test

class SomeTest {

	@Test
	fun `I can start a new game`() {
		Game(1L, "Cpt Hook")
	}
}