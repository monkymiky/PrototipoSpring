package com.unipd.synclab.project1.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unipd.synclab.project1.model.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    
}
