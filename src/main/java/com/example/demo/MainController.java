package com.example.demo;

import com.example.demo.entity.Entry;
import com.example.demo.entity.Tag;
import com.example.demo.form.EntryForm;
import com.example.demo.form.TagItem;
import com.example.demo.repository.EntryRepository;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class MainController {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    TagRepository tagRepository;

    @GetMapping("")
    public String entryList(Model model){
        model.addAttribute("entryList", entryRepository.findAll());

        return "entry-list";
    }

    @GetMapping("/entry/{id}/edit")
    @Transactional(readOnly = true)
    public String entryEditForm(Model model, @PathVariable("id")  Entry entry){

        EntryForm form = new EntryForm();
        form.setName(entry.getName());
        List<Tag> tags = entry.getTagList();
        List<TagItem> tagItems = tags.stream().map(TagItem::fromTag).collect(toList());
        form.setTags(tagItems);

        model.addAttribute("formData", form);
        model.addAttribute("entry", entry);

        return "entry-edit";
    }

    @PostMapping("/entry/{id}/edit")
    public String entryEdit(
            @ModelAttribute("formData") EntryForm formData,
            BindingResult bindingResult,
            @PathVariable("id") Entry entry,
            Model model
    ){
        model.addAttribute("entry", entry);

        if (bindingResult.hasErrors()) {
            return "entry-edit";
        }
        entry.setName(formData.getName());

        List<Long> tagIds = formData.getTags().stream().map(TagItem::getId).collect(toList());
        entry.setTagList(tagRepository.findAllByIdIn(tagIds));

        entryRepository.save(entry);
        return String.format("redirect:/entry/%d/", entry.getId());
    }


    @ModelAttribute("tags")
    public List<TagItem> getTags() {
        return tagRepository.findAll().stream().map(TagItem::fromTag).collect(toList());
    }
}
