package de.bbk.cachemeifyoucan.repository;

import de.bbk.cachemeifyoucan.dao.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDao, Long> {
}