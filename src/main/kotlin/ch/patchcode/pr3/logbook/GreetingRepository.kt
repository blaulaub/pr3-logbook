package ch.patchcode.pr3.logbook

import org.springframework.data.repository.CrudRepository

interface GreetingRepository : CrudRepository<Greeting, Long>