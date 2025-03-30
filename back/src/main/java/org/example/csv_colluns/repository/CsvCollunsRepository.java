package org.example.csv_colluns.repository;

import org.example.csv_colluns.model.CsvColluns;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CsvCollunsRepository  extends JpaRepository<CsvColluns, Long> {
}
