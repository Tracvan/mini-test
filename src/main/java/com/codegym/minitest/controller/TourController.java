package com.codegym.minitest.controller;

import com.codegym.minitest.model.Tour;
import com.codegym.minitest.model.Type;
import com.codegym.minitest.service.ITourService;
import com.codegym.minitest.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/tour")
class TourController {
    @Autowired
    private ITourService tourService;

    @Autowired
    private ITypeService typeService;


    @GetMapping
    public String showHome(@RequestParam(name = "page", defaultValue = "0") int pageIndex,
                           @RequestParam(name = "size", defaultValue = "2") int pageSize,
                           Model model){
        Page<Tour> tours = tourService.findAll(pageIndex, pageSize);
        model.addAttribute("tours", tours);
        return "/home";
    }

    @GetMapping("/find")
    public String showResult(@RequestParam("destination") String name,
                             @PageableDefault(size = 1) Pageable pageable,
                             Model model){
        Page<Tour> tours = tourService.findTourByDestinationIsContaining(pageable, name);
        model.addAttribute("tours", tours);
        return "/home";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable Long id, Model model) throws NoSuchElementException {
            Tour tour = tourService.findById(id).get();
            Iterable<Type> types = typeService.findAll();
            model.addAttribute("types",types);
            model.addAttribute("tour",tour);
            return "/update";
        }

    @PostMapping("/edit")
    public String update(@Valid Tour tour, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            Iterable<Type> types = typeService.findAll();

            model.addAttribute("types",types);
            return "/update";
        }
        tourService.save(tour);
        return "redirect:/tour";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws NoSuchElementException{
        tourService.remove(id);
        return "redirect:/tour";
    }
    @GetMapping("/create")
    public String create(Model model){
        Tour tour = new Tour();
        Iterable<Type> types = typeService.findAll();

        model.addAttribute("tour", tour);
        model.addAttribute("types",types);
        return "/create";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("tour") Tour tour, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            Iterable<Type> types = typeService.findAll();
            model.addAttribute("types",types);
            return "/create";
        }
            tourService.save(tour);
            return "redirect:/tour";


    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handlerException(){
        return "/errors";
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handlerException2(){
        return "/errors";
    }
}
