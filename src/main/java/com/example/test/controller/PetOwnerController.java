package com.example.test.controller;

import com.example.test.model.pets.PetOwner;
import com.example.test.repos.PetOwnerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PetOwnerController {
    PetOwnerRepo petOwnerDao;

    public PetOwnerController(PetOwnerRepo petOwnerDao) {
        this.petOwnerDao = petOwnerDao;
    }

    @GetMapping("/pet-owners")
    public String showOwners(Model model){
        List<PetOwner> petOwners = petOwnerDao.findAll();
        model.addAttribute("petOwners", petOwners);
        return "owners";
    }

    @GetMapping("/test")
    @ResponseBody
    public String testing(){
        return "testing";
    }

}
