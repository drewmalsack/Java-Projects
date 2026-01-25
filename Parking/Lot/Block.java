package Parking.Lot;

public class Block {
    
    //holds the space using this block, if there is one.
    private Space space;

    public Block(){

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

}
