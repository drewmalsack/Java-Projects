package spring.lot.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import spring.lot.exception.IncompatibleSizeException;
import spring.lot.exception.SpaceOccupiedException;

@Entity
public class Space {

    public enum Size{
        SMALL,
        MEDIUM,
        LARGE
    }
    
    //List of blocks(physical space) that the space uses
    @JsonManagedReference
    @OneToMany(mappedBy = "space") //defines the owner of the relationship and prevents a join table being created
    private List<Block> blocks;

    @Id
    private String spaceId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_plate")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private Size size;

    public Space(){}

    public Space(List<Block> blocks) {
        this.blocks = blocks;
        this.computeSize();
        spaceId = "space-"+blocks.get(0).getxCoord()+blocks.get(0).getyCoord()+blocks.get(blocks.size()-1).getxCoord()+blocks.get(blocks.size()-1).getyCoord();
    }

    public void releaseBlocks(){
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setSpace(null);
        }
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public void setBlocks(List<Block> blocks){
        this.blocks = blocks;
    }

    public List<Block> getBlocks(){
        return this.blocks;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isOccupied(){
        return this.vehicle != null;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size){
        this.size = size;
    }

    private void computeSize(){
        List<List<Block>> tempBlocks = new ArrayList<>();
        List<Block> tempArray = null;
        int tempCoord = -1;

        for(Block b : blocks){
            if(b.getxCoord() != tempCoord){
                if(tempArray != null)
                    tempBlocks.add(tempArray);
                tempArray = new ArrayList<>();
                tempCoord = b.getxCoord();
            }
            tempArray.add(b);
        }
        tempBlocks.add(tempArray);
        System.out.println(tempBlocks.size());
        System.out.println(tempBlocks.get(0).size());
        if(tempBlocks.size() >= 3 && tempBlocks.get(0).size() >= 3)
            this.size = Size.LARGE;
        else if(tempBlocks.size() >= 2 && tempBlocks.get(0).size() >= 2)
            this.size = Size.MEDIUM;
        else
            this.size = Size.SMALL;
    }

    public void Park(Vehicle vehicle){
        if(this.isOccupied())
            throw new SpaceOccupiedException("This space is already occupied.");

        if(!this.canFit(vehicle))
            throw new IncompatibleSizeException("The vehicle is too large for this space.");
        this.vehicle = vehicle;
        vehicle.setSpace(this);
    }

    public void Vacate(){
        if(vehicle != null){
            vehicle.setSpace(null);
            vehicle = null;
        }
    }

    public boolean canFit(Vehicle vehicle){
        if(this.size == Size.LARGE)
            return true;
        else if(this.size == Size.MEDIUM && vehicle.getSize() != Vehicle.Car_Size.TRUCK)
            return true;
        else if(this.size == Size.SMALL && vehicle.getSize() == Vehicle.Car_Size.COMPACT)
            return true;
        else
            return false;
    }

    public String toString(){
        if(blocks.size() == 0)
            return "this space isnt assigned to any blocks, and thus doesnt exist";

        StringBuilder str = new StringBuilder();
        //String containerBlocks = "(" + blocks.get(0).getxCoord() + ", " + blocks.get(0).getyCoord() + ")";
        str.append("(" + blocks.get(0).getxCoord() + ", " + blocks.get(0).getyCoord() + ")");
        
        if(blocks.size() > 1){
            for(int i=1;i<blocks.size();i++){
                str.append(", "+ "(" + blocks.get(i).getxCoord() + ", " + blocks.get(i).getyCoord() + ")");
            }
        }
        return "This space is located at: ("+blocks.get(0).getxCoord() + ", " + blocks.get(0).getyCoord()
        + ") and takes up block(s): " + str.toString();
    }
}
