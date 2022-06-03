package com.template.springapptemplate.repo;

import com.template.springapptemplate.model.Foo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FooRepository extends PagingAndSortingRepository<Foo, Long> {
}
