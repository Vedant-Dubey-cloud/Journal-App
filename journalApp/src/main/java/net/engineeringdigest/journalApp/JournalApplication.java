package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();

        // Print active profiles
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            System.out.println("Active profile: " + activeProfiles[0]);
        } else {
            System.out.println("No active profile set.");
        }

        // Print the actual server port
        String serverPort = environment.getProperty("server.port");
        System.out.println("Server is running on port: " + serverPort);
    }
}
