package com.tecnocampus.examsimulation.persistence;

import com.tecnocampus.examsimulation.domain.Kingdom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KingdomRepository extends CrudRepository<Kingdom, Long> {
}
