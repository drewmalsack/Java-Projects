package spring.lot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.lot.model.Lot;
import spring.lot.service.LotService;

@RestController
public class LotController{

    @Autowired
    public LotService service;

    @RequestMapping("/status")
    public String LotStatus(){
        return service.getLotStatus();
    }

    @RequestMapping("/addSpace")
    public String addSpace(@RequestParam int length, @RequestParam int width, @RequestParam int x, @RequestParam int y){
        return service.addSpace(length, width, x, y);
    }
}