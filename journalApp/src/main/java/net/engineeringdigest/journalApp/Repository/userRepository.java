package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepository extends MongoRepository<User,ObjectId> {
    static void deleteByUserName(String name) {
    }

    User findByUserName(String userName); // Uppercase N
}
