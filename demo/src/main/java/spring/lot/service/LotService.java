package spring.lot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.lot.model.Block;
import spring.lot.model.Lot;
import spring.lot.model.Space;
import spring.lot.repository.BlockRepository;
import spring.lot.repository.LotRepository;
import spring.lot.repository.SpaceRepository;

@Service
public class LotService {

    //public final Lot lot = new Lot(10, 10);

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private BlockRepository blockRepository;

    public Lot getLot(){
        return lotRepository.findAll().stream().findFirst().orElse(null);
    }

    public List<Space> getSpaces(){
        Lot lot = getLot();
        if(lot == null)
            return null;
        return lot.getSpaces();
    }

    public Space addSpace(int length, int width, int x, int y){
        Lot lot = getLot();
        if(lot == null)
            return null;
        Space newSpace = lot.addSpace(length, width, x, y);
        if(newSpace != null){
            lotRepository.save(lot);
            return newSpace;
        }
        return null;
    }

    public boolean removeSpace(String spaceId){
        Lot lot = getLot();
        if(lot == null)
            return false;
        boolean removed = getLot().removeSpace(spaceId);
        if(removed){
            lotRepository.save(lot);
            return removed;
        }
        return removed;
    }

    public boolean removeSpace(int x, int y){
        Lot lot = getLot();
        if(lot == null)
            return false;
        boolean removed = getLot().removeSpace(x, y);
        if(removed){
            lotRepository.save(lot);
            return removed;
        }
        return removed;
    }
}
