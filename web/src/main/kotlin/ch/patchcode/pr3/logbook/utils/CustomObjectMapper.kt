package ch.patchcode.pr3.logbook.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.databind.SerializationFeature

object CustomObjectMapper : ObjectMapper() {
	init {
		registerKotlinModule()
		registerModule(JavaTimeModule())
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
	}
}