package Parking.Lot;

import java.util.List;

public class Space {

    //List of blocks(physical space) that the space uses
    private List<Block> blocks;

    public Space(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void setBlocks(List<Block> blocks){
        this.blocks = blocks;
    }

    public List<Block> getBlocks(){
        return this.blocks;
    }
}
