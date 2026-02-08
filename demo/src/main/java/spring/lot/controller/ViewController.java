package spring.lot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.lot.exception.ParkingException;
import spring.lot.model.Space;
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

    @PostMapping("/lot-view")
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
            redirectAttributes.addFlashAttribute("result", ex.getMessage());
        }
    
        return "redirect:/lot-view";
    }
}
