package spring.lot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.lot.model.Block;
import spring.lot.model.Lot;
import spring.lot.model.Space;
import spring.lot.service.LotService;

@RestController
public class LotController{

    @Autowired
    public LotService service;

    @RequestMapping("/status")
    public Lot getLot(){
        return service.getLot();
    }

    @RequestMapping("/spaces")
    public List<Space> getAllSpaces(){
        return service.getSpaces();
    }

    @RequestMapping("/addSpace")
    public ResponseEntity<String> addSpace(@RequestParam int length, @RequestParam int width, @RequestParam int x, @RequestParam int y){
        Space space = service.addSpace(length, width, x, y);
        if(space != null)
            return new ResponseEntity<>("Space created successfully.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Space was not created.", HttpStatus.BAD_REQUEST);
        
        //return service.addSpace(length, width, x, y);
    }

    @RequestMapping("/removeSpaceById")
    public String removeSpaceById(@RequestParam String id){
        boolean result = service.removeSpace(id);
        if(result)
            return "Space: "+id+" has been removed.";
        else
            return "Space: "+id+" not found.";
    }

   /* @PostMapping("/removeSpaceByBlock")
    public String removeSpaceByBlock(@RequestBody Block block){
        boolean result = service.removeSpace(block);
        if(result)
            return "The space using that block has been removed.";
        else
            return "That block either doesn't exist or does not have a space associated with it.";
    } Bad method, use DTO instead of just use the x and y coords method */

    @RequestMapping("/removeSpaceByCoords")
    public String removeSpaceByCoords(@RequestParam int x, @RequestParam int y){
        boolean result = service.removeSpace(x, y);
        if(result)
            return "Space using coordinates: "+x+", "+y+" has been removed.";
        else
            return "Space not found with those coordinates.";
    }
}