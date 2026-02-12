package spring.lot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Space {

    //List of blocks(physical space) that the space uses
    @JsonManagedReference
    @OneToMany(mappedBy = "space") //defines the owner of the relationship and prevents a join table being created
    private List<Block> blocks;

    @Id
    private String spaceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_plate")
    private Vehicle vehicle;

    public Space(){}

    public Space(List<Block> blocks) {
        this.blocks = blocks;
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
