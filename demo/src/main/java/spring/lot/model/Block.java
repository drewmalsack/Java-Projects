package spring.lot.model;

import org.springframework.stereotype.Component;

public class Block {
    
    //holds the space using this block, if there is one.
    private Space space;
    
    //the coordinates of the block
    private int xCoord;
    private int yCoord;

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
