package com.djachtoma.repository;

import com.djachtoma.model.Physician;
import org.springframework.data.repository.CrudRepository;

public interface PhysicianRepository extends CrudRepository<Physician,String> {
}
