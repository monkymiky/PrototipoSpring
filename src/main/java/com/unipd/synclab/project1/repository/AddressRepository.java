package com.unipd.synclab.project1.repository;

import com.unipd.synclab.project1.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}