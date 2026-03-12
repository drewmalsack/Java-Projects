package spring.lot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.lot.exception.ParkingException;
import spring.lot.model.Space;
import spring.lot.model.Vehicle;
import spring.lot.service.LotService;

@Controller
public class ViewController {
    
    @Autowired
    private LotService lotService;

    @GetMapping("/lot-view")
    public String viewLot(Model model){

        model.addAttribute("lot", lotService.getLot());
        return "lot-view";
    }

    @PostMapping("/add-space")
    public String processForm(RedirectAttributes redirectAttributes, @RequestParam int x_coord, @RequestParam int y_coord, @RequestParam int length, @RequestParam int width){

        try{
            lotService.addSpace(length, width, x_coord, y_coord);
        } catch(ParkingException ex){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
            
        return "redirect:/lot-view";
    }

    @PostMapping("/delete-space")
    public String deleteSpace(RedirectAttributes redirectAttributes, @RequestParam String spaceId){

        try{
            lotService.removeSpace(spaceId);
            redirectAttributes.addFlashAttribute("result", "Space removed successfully.");
        } catch(ParkingException ex){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
    
        return "redirect:/lot-view";
    }

    @PostMapping("/vacate")
    public String vacateSpace(RedirectAttributes redirectAttributes, @RequestParam String spaceId){

        try{
            lotService.vacateSpace(spaceId);
            redirectAttributes.addFlashAttribute("result", "Vehicle removed successfully.");
        } catch(ParkingException ex){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
    
        return "redirect:/lot-view";
    }

    @RequestMapping("/park")
    public String parkVehicle(
        @RequestParam String make,
        @RequestParam String model,
        @RequestParam String plate,
        @RequestParam Vehicle.Car_Size size,
        @RequestParam String spaceId,
        RedirectAttributes redirectAttributes
    ){
        try{
            lotService.parkVehicle(spaceId, plate, make, model, size);
            redirectAttributes.addFlashAttribute("result", "Vehicle parked successfully.");
        }catch(ParkingException ex){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/lot-view";
    }

    @RequestMapping("/search")
    public String searchVehicle(String plate, RedirectAttributes ra){
        try{
            String spaceId = lotService.findSpaceByPlate(plate).getSpaceId();
            ra.addFlashAttribute("highlightId", spaceId);
        } catch(ParkingException e){
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/lot-view";
    }
}
