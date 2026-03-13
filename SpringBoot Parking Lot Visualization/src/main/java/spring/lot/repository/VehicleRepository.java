package spring.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.lot.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String>{

}
