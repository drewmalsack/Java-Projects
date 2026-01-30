package spring.lot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.lot.model.Block;
import spring.lot.model.Lot;
import spring.lot.model.Space;

@Service
public class LotService {

    public final Lot lot = new Lot(10, 10);

    public String getLotStatus(){
        return lot.toString();
    }

    public List<Space> getSpaces(){
        return lot.getSpaces();
    }

    public String addSpace(int length, int width, int x, int y){
        Space newSpace = lot.addSpace(length, width, x, y);
        if(newSpace != null)
            return newSpace.toString();
        return "Not all blocks are available to create that space.";
    }

    public boolean removeSpace(String spaceId){
        return lot.removeSpace(spaceId);
    }

    public boolean removeSpace(int x, int y){
        return lot.removeSpace(x, y);
    }
}
