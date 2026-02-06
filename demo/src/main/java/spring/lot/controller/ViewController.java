package spring.lot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Space newSpace = lotService.addSpace(length, width, x_coord, y_coord);
        if(newSpace == null)
            redirectAttributes.addFlashAttribute("errorMessage", "Could not create space. Check for overlaps or boundaries.");
        return "redirect:/lot-view";
    }

    @PostMapping("/delete-space")
    public String deleteSpace(RedirectAttributes redirectAttributes, @RequestParam String spaceId){

        boolean removed = lotService.removeSpace(spaceId);
        if(removed)
            redirectAttributes.addFlashAttribute("result", "Space removed successfully.");
        else
            redirectAttributes.addFlashAttribute("result", "Space failed to be removed.");
        return "redirect:/lot-view";
    }
}
