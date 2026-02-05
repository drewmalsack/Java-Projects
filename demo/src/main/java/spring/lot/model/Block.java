package spring.lot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Block {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //holds the space using this block, if there is one.
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Space space;
    
    //the coordinates of the block
    private int xCoord;
    private int yCoord;

    public Block() {

    }

    public Block(int x, int y){
        this.xCoord = x;
        this.yCoord = y;
    }

    public boolean isAvailable(){
        if(space == null){
            return true;
        }
        return false;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setSpace(Space space){
        this.space = space;
    }

    public Space getSpace(){
        return this.space;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int[] getCoords(){
        return new int[]{xCoord, yCoord};
    }

}
