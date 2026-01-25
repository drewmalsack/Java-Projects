package Parking.Lot;

public class Block {
    
    //holds the space using this block, if there is one.
    private Space space;

    public Block(){

    }

    public void setSpace(Space space){
        this.space = space;
    }

    public Space getSpace(){
        return this.space;
    }

}
