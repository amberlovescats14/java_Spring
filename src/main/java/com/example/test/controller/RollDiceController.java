package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class RollDiceController {
    private Random random = new Random();

    @GetMapping("/roll-dice")
    public String showRollDice(Model model){
        model.addAttribute("rolled", false);
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String guess(
            @PathVariable int guess,
            Model model
    ) {
        int correct = 0;
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int diceNumber = random.nextInt(6)+1;
            if(diceNumber == guess) correct++;
            dice.add(diceNumber);
        }
        model.addAttribute("diceRolls", dice);
        model.addAttribute("guess", guess);
        model.addAttribute("rolled", true);

        String message = correct == 0 ? "Sorry, none right" : "You got "+correct+" right!";
        model.addAttribute("message", message);

        return "roll-dice";
    }
}
