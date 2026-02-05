package spring.lot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Lot{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Transient //Block array will not be persisted
    private Block[][] blocks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Cascade All ensures blocks are saved when the lot is saved, Fetch Eager fetches all block data when initializing the Lot. Decision made as all important lot methods use the block data so lazy loading doesnt make sense yet
    @JoinColumn(name = "lot_id")
    private List<Block> blockList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //makes sure to remove the space from the db when saving the lot after the space was removed from the list
    @JoinColumn(name = "lot_id")
    private List<Space> spaces; //can update this list to a hashmap, making the removeSpace method use less time finding the space through its id.

    public Lot(){

    }

    public Lot(int length, int width){
        blocks = new Block[length][width];
        blockList = new ArrayList<>();
        spaces = new ArrayList<>();

        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++){
                blocks[i][j] = new Block(i,j);
                blockList.add(blocks[i][j]);
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

    public boolean removeSpace(int x, int y){
        Block tempBlock = blocks[x][y];
        Space tempSpace = tempBlock.getSpace();
        
        if(tempSpace == null)
            return false;
        tempSpace.releaseBlocks();
        spaces.remove(tempSpace);
        return true;
    }

    public void clearAllSpaces(){
        List<String> ids = spaces.stream()
            .map(Space::getSpaceId)
            .toList();

        for (String id : ids) {
            removeSpace(id);
        }
    }

    public Block[][] getBlocks(){
        return this.blocks;
    }

    //this will most likely change or be replaced with a block[][] generation method
    /*public void setBlocks(Block[][] blocks){
        this.blocks = blocks;
    }*/

    @jakarta.persistence.PostLoad
    private void fillGrid(){
        if(this.blockList != null){
            this.blocks = new Block[10][10]; //will need to be revisited if project is updated to create different sized lots
            for(Block b : blockList){
                blocks[b.getxCoord()][b.getyCoord()] = b;
            }
        }
    }

    public void setSpaces(List<Space> spaces){
        this.spaces = spaces;
    }

    public List<Space> getSpaces(){
        return this.spaces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    private void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }

    public String toString(){
        return "Blocks: "+ (blocks.length * blocks[0].length)+ ", Spaces: "+spaces.size();
    }
}