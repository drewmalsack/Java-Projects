package spring.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.lot.model.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long>{

}
