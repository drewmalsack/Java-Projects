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
