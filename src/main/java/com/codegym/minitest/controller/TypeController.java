package com.codegym.minitest.controller;

import com.codegym.minitest.model.Type;
import com.codegym.minitest.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @GetMapping
    public String showAll(Model model) {
        Iterable<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        return "/showtype";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws NoSuchElementException {
        Type type = typeService.findById(id).get();

        model.addAttribute("type", type);
        return "/editForm";
    }


    @PostMapping("/edit")
    public String update(@Valid Type type, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", type);
            return "/editForm";
        }
        typeService.save(type);
        return "redirect:/type";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Type type = new Type();
        model.addAttribute("type", type);
        return "/createForm";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Type type, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", type);
            return "/createForm";
        }
        typeService.save(type);
        return "redirect:/type";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws NoSuchElementException {
        typeService.remove(id);
        return "redirect:/type";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handlerException(){
        return "/errors";
    }

}
