package spring.lot.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Space {

    //List of blocks(physical space) that the space uses
    @JsonManagedReference
    private List<Block> blocks;
    private String spaceId;

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
