package net.engineeringdigest.journalApp.services;
import net.engineeringdigest.journalApp.Repository.JournalEntryrepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryrepository journalEntryrepository;
    @Autowired
     private static final Logger logger= (Logger) LoggerFactory.getLogger(JournalEntryService.class);

//    @Transactional
    private UserService userService;
    public void saveNewEntry(JournalEntry journalEntry, String username)
    {
        User user=userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalEntryrepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryrepository.save(journalEntry);
    }
    public List<JournalEntry> getAll()
    {
        return journalEntryrepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId myId)
    {
        return journalEntryrepository.findById(myId);
    }

    @Transactional
    public void deleteById(ObjectId myId, String userName)
    {
    try {
        User user = userService.findByUserName(userName);
        boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        if (removed)
        {
            userService.saveEntry(user);
            journalEntryrepository.deleteById(myId);
        }
    }
        catch(Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occurred during deleting entry");
        }
    }



//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String userName=authentication.getName();



    public List<JournalEntry> findByUserName(String userName)
    {
        User user= userService.findByUserName(userName);
        return user.getJournalEntries();
    }
}
