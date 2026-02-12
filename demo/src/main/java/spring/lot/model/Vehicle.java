package spring.lot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {

    public enum Car_Size {
        COMPACT,
        MID_SIZE,
        SUV,
        TRUCK
    }

    private String make;
    private String model;

    @Id
    private String plate;

    @Enumerated(EnumType.STRING)
    private Car_Size size;

    @OneToOne(mappedBy = "vehicle")
    private Space space;

    public Vehicle(){}
    
    public Vehicle(String make, String model, String plate, Car_Size size){
        this.make=make;
        this.model=model;
        this.plate=plate;
        this.size=size;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Enum<Car_Size> getSize() {
        return size;
    }

    public void setSize(Car_Size size) {
        this.size = size;
    }

    public void setSpace(Space space){
        this.space=space;
    }

    public Space getSpace() {
        return space;
    }


}
