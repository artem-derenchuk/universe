package com.test.controller;


import com.test.model.Lord;
import com.test.model.Planet;
import com.test.repository.LordRepository;
import com.test.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/universe")
public class LordController {

    private LordRepository lordRepo;
    private PlanetRepository planetRepo;

    @Autowired
    public LordController(LordRepository lordRepo, PlanetRepository planetRepo) {
        this.lordRepo = lordRepo;
        this.planetRepo = planetRepo;
    }

    @GetMapping
    public String showLordsAndPlanets(Model model) {
        List<Lord> lords = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        lordRepo.findAll().forEach(lords::add);
        planetRepo.findAll().forEach(planets::add);
        model.addAttribute("lords",lords);
        model.addAttribute("planets",planets);
        return  "universe";
    }
    @GetMapping("lord/add")
    public String showAddLordForm(Lord lord) {
        return "lordadd";
    }
    @PostMapping("lord/add")
    public String addLord(Lord lord) {
        Long newId = lordRepo.findMaxId() + 1;
        lord.setId(newId);
        lordRepo.save(lord);
        return "redirect:/universe";
    }
    @GetMapping("planet/add")
    public String showAddPlanetForm(Planet planet) {
        return "planetadd";
    }

    @PostMapping("planet/add")
    public String addPlanet(Planet planet) {
        Long newId = planetRepo.findMaxId() + 1;
        planet.setId(newId);
        planetRepo.save(planet);
        return "redirect:/universe";
    }
    @GetMapping("/planet/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        planetRepo.deleteById(id);
        return "redirect:/universe";
    }
    @GetMapping("/planet/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Planet> planet = planetRepo.findById(id);
        List<Lord> lords = new ArrayList<>();
        lordRepo.findAll().forEach(lords::add);
        model.addAttribute("updatedPlanet", planet.get());
        model.addAttribute("lords",lords);
        return "setLordToPlanet";
    }
    @PostMapping("/planet/update/{id}")
    public String setLordToPlanet(@PathVariable("id") Long id, Planet planet) {
        planetRepo.save(planet);
        return "redirect:/universe";
    }
    @GetMapping("/lord/top/young")
    public String showTopTenYoungestLords(Model model) {
        List <Lord> lords = lordRepo.findTop10ByOrderByAgeAsc();
        model.addAttribute("younglords",lords);
        return "younglords";
    }
    @GetMapping("/lord/slacker")
    public String showSlackersLords(Model model) {
        List <Lord> lords = lordRepo.findSlackerLords();
        model.addAttribute("slackersLords", lords);
        return "slackersLords";
    }
}
