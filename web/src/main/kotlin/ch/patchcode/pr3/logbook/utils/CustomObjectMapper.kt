package ch.patchcode.pr3.logbook.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object CustomObjectMapper : ObjectMapper() {
	init {
		registerKotlinModule()
		registerModule(JavaTimeModule())
	}
}