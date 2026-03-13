package spring.lot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.lot.exception.SpaceNotFoundException;
import spring.lot.exception.VehicleNotFoundException;
import spring.lot.model.Block;
import spring.lot.model.Lot;
import spring.lot.model.Space;
import spring.lot.model.Vehicle;
import spring.lot.repository.BlockRepository;
import spring.lot.repository.LotRepository;
import spring.lot.repository.SpaceRepository;
import spring.lot.repository.VehicleRepository;

@Service
@Transactional
public class LotService {

    //public final Lot lot = new Lot(10, 10);

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Lot getLot(){
        return lotRepository.findAll().stream().findFirst().orElseGet(() -> {
            Lot masterLot = new Lot(10, 10);
            return lotRepository.save(masterLot);
        });
    }

    public void vacateSpace(String spaceId){
        Space space = spaceRepository.findById(spaceId).orElseThrow(() -> new SpaceNotFoundException("No space found with ID: "+spaceId));
        space.Vacate();
        spaceRepository.save(space);

    }

    public Space findSpaceByPlate(String plate){
        Vehicle vehicle = vehicleRepository.findById(plate).orElseThrow(() -> new VehicleNotFoundException("No vehicle found with plate: "+plate));
        if(vehicle.getSpace() == null)
            throw new SpaceNotFoundException("Vehicle with plate: "+plate+" is not occupying a space.");
        return vehicle.getSpace();
    }

    public long getSpaceCount(){
        return spaceRepository.count();
    }

    public long getOccupiedCount(){
        return spaceRepository.countByVehicleIsNotNull();
    }

    public List<Space> getSpaces(){
        Lot lot = getLot();

        return lot.getSpaces();
    }

    public Space addSpace(int length, int width, int x, int y){
        Lot lot = getLot();

        Space newSpace = lot.addSpace(length, width, x, y);

        lotRepository.save(lot);
        return newSpace;
    }

    public boolean removeSpace(String spaceId){
        Lot lot = getLot();

        boolean removed = getLot().removeSpace(spaceId);

        lotRepository.save(lot);
        return removed;
    }

    public boolean removeSpace(int x, int y){
        Lot lot = getLot();

        boolean removed = getLot().removeSpace(x, y);

        lotRepository.save(lot);
        return removed;
    }

    public void parkVehicle(String spaceId, String plate, String make, String model, Vehicle.Car_Size size){
        Space space = spaceRepository.findById(spaceId).orElseThrow(() -> new SpaceNotFoundException("Space with id: "+spaceId+" does not exist."));
        space.Park(new Vehicle(make, model, plate, size));
        spaceRepository.save(space);
    }
}
