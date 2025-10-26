package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import net.engineeringdigest.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    UserService userService;
    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping // localhost:8080/journal GET
    public ResponseEntity<?> getJournalEntryByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            journalEntryService.saveNewEntry(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


//    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
//    {
//        try {
//            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveEntry(myEntry);
//            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    public ResponseEntity<JournalEntry>

//    public JournalEntry createEntry(@RequestBody JournalEntry myEntry)
//    {   myEntry.setDate(LocalDateTime.now());
//        journalEntryService.saveEntry(myEntry);
//        return myEntry;
//    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> content=user.getJournalEntries();
        List<JournalEntry> collect = user.getJournalEntries().stream()
                .filter(x -> x.getId().equals(myid))
                .collect(Collectors.toList());

        if(collect!=null)
        { Optional<JournalEntry> journalEntry = journalEntryService.getById(myid);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}/{username}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid, @PathVariable String username) {
        journalEntryService.deleteById(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("id/{myid}/{username}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry, @PathVariable String username) {
        JournalEntry old = journalEntryService.getById(myid).orElse((null));
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            return new ResponseEntity<>(old, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

}
