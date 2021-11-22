package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Event Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Add New Event Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "All Event Categories");
            model.addAttribute("errorMsg", "Bad");
            return "eventCategories/create";
        }
        EventCategory newEventCategory = new EventCategory(eventCategory.getName());
        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }

}
