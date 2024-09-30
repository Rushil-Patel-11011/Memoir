package com.rushil.Memoir.controller;

import com.rushil.Memoir.entity.JournalEntry;
import com.rushil.Memoir.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myID}")
    public JournalEntry getbyID(@PathVariable ObjectId myID)
    {
        return journalEntryService.findById(myID).orElse(null);
    }

    @DeleteMapping("id/{myID}")
    public boolean deletebyID(@PathVariable ObjectId myID)
    {
        journalEntryService.deleteById(myID);
        return true;
    }

    @PutMapping("id/{myID}")
    public JournalEntry updatebyID(@PathVariable ObjectId myID,@RequestBody JournalEntry newEntry)
    {
        JournalEntry old = journalEntryService.findById(myID).orElse(null);
        if (old != null){
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
