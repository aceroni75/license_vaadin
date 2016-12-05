package com.genedata.expressionist.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alessio Ceroni
 */

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

	List<License> findByRecipientContainingIgnoreCase(String lastName);
}
