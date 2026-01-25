package Parking.Lot;

import java.util.ArrayList;
import java.util.List;

public class Lot{

    private Block[][] blocks;
    private List<Space> spaces;

    public Lot(int length, int width){
        blocks = new Block[length][width];
        spaces = new ArrayList<>();

        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++){
                blocks[i][j] = new Block(i,j);
            }
        }
    }

    //method to create a space in the lot and assign blocks to it.
    public Space addSpace(int length, int width, int x, int y){

        //holds the blocks that will be used for the proposed space
        List<Block> spaceBlocks = new ArrayList<>();

        Space newSpace;

        //checks if the space would be created out of bounds of the lots length and width
        // or if the method is called to create a space that takes no blocks
        if(x<0 || y<0 || x+length>blocks.length || y+width>blocks[0].length || length == 0 || width == 0){
            return null;
        }

        //for loop to check if all blocks in proposed space are available
        for(int i=0;i<length; i++){
            for(int j=0;j<width;j++){
                Block tempBlock = blocks[x+i][y+j];
                if(!tempBlock.isAvailable())
                    return null;
                spaceBlocks.add(tempBlock);
            }
        }

        //initialize the new space
        newSpace = new Space(spaceBlocks);

        //for loop to link blocks to the space
        for(int i=0;i<spaceBlocks.size(); i++){
            Block tempBlock = spaceBlocks.get(i);
            tempBlock.setSpace(newSpace);
        }

        spaces.add(newSpace);

        return newSpace;
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