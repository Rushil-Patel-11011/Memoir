package com.rushil.Memoir.controller;

import com.rushil.Memoir.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return "Entry done";
    }

    @GetMapping("id/{myID}")
    public JournalEntry getbyID(@PathVariable Long myID)
    {
        return journalEntries.get(myID);
    }

    @DeleteMapping("id/{myID}")
    public JournalEntry deletebyID(@PathVariable Long myID)
    {
        return journalEntries.remove(myID);
    }

    @PutMapping("id/{myID}")
    public JournalEntry updatebyID(@PathVariable Long myID,@RequestBody JournalEntry myEntry)
    {
        return journalEntries.put(myID,myEntry);
    }
}
