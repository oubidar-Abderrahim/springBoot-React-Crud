package com.satityr.spring.react.crudapp;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.satityr.spring.react.crudapp.model.Event;
import com.satityr.spring.react.crudapp.model.Group;
import com.satityr.spring.react.crudapp.model.GroupRepository;

@Component
public class Initializer implements CommandLineRunner {
	
	private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

	@Override
	public void run(String... args) throws Exception {

		Stream.of("Morocco JUG", "Germany JUG", "USA JUG", "India JUG")
			  .forEach(name -> repository.save(new Group(name))
        );

        Group djug = repository.findByName("Morocco JUG");
        
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        
        djug.setEvents(Collections.singleton(e));
        
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
		
	}

}
