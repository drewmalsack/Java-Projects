package spring.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.lot.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long>{

}