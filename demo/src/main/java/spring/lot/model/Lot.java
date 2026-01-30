package spring.lot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

public class Lot{

    private Block[][] blocks;
    private List<Space> spaces; //can update this list to a hashmap, making the removeSpace method use less time finding the space through its id.

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

    public boolean removeSpace(String spaceId){

        /*boolean removed = false;
        for(int i=0;i<spaces.size();i++){
            if(spaces.get(i).getSpaceId().equals(spaceId)){
                spaces.get(i).releaseBlocks();
                spaces.remove(i);
                removed = true;
                break;
            }
        }
        return removed;*/
        Space tempSpace = spaces.stream().filter(s -> s.getSpaceId().equals(spaceId)).findFirst().orElse(null);
        if(tempSpace == null)
            return false;
        tempSpace.releaseBlocks();
        spaces.remove(tempSpace);
        return true;
    }

    public boolean removeSpace(Block block){

        Space tempSpace = block.getSpace();
        
        if(tempSpace == null)
            return false;
        tempSpace.releaseBlocks();
        spaces.remove(tempSpace);
        return true;
    }

    public boolean removeSpace(int x, int y){
        Block tempBlock = blocks[x][y];
        Space tempSpace = tempBlock.getSpace();
        
        if(tempSpace == null)
            return false;
        tempSpace.releaseBlocks();
        spaces.remove(tempSpace);
        return true;
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

    public String toString(){
        return "Blocks: "+ (blocks.length * blocks[0].length)+ ", Spaces: "+spaces.size();
    }
}