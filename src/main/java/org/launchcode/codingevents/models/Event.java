package org.launchcode.codingevents.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity{

    @NotBlank(message="Name must not be blank")
    @Size(min = 3, max= 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToOne
    @NotNull(message= "Category is required")
    private EventCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails details;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event() {
    }

    public Event(String name, EventCategory category) {
        this.name = name;
        this.category = category;
    }

    public EventDetails getDetails() {
        return details;
    }

    public void setDetails(EventDetails details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }

}
