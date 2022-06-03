package com.template.springapptemplate.service;

import com.template.springapptemplate.dto.FooDTO;
import com.template.springapptemplate.model.Foo;
import com.template.springapptemplate.repo.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableScheduling // TODO: Remove this if you do not want to use the @Scheduled(fixedRate = 10000) annotation.
public class FooService {
    private final Logger logger = LoggerFactory.getLogger(FooService.class);

    private final FooRepository fooRepository;

    @Autowired
    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    /**
     * Returns a FooDTO containing the data of Foo Id: [requestedId]
     * @param id of the Foo to look for.
     * @return DTO with corresponding data.
     * @throws Exception when the Id was not found in the database.
     */
    public FooDTO findById(long id) throws Exception {
        logger.info("Find by id: {}", id);

        Optional<Foo> optionalFoo = fooRepository.findById(id);
        if (optionalFoo.isEmpty()) throw new Exception("Requested Id not found.");

        Foo foo = optionalFoo.get();

        return FooDTO.builder()
                .id(foo.getId())
                .data(foo.getData())
                .build();
    }
}
