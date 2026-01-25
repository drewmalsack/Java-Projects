package Parking.Lot;

import java.util.List;

public class Lot{

    private Block[][] blocks;
    private List<Space> spaces;

    public Lot(int length, int width){
        blocks = new Block[length][width];
    }

    public void setBlocks(Block[][] blocks){
        this.blocks = blocks;
    }

    public Block[][] getBlocks(){
        return this.blocks;
    }

    public void setSpaces(List<Space> spaces){
        this.spaces = spaces;
    }

    public List<Space> getSpaces(){
        return this.spaces;
    }
}