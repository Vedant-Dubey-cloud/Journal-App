package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryrepository extends MongoRepository <JournalEntry, ObjectId> {

//    List<JournalEntry> findByName(String userName);
}
