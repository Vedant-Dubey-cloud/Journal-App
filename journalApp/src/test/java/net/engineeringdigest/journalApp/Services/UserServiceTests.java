package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.Repository.userRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest


public class UserServiceTests {
    @Autowired
    userRepository userRepository;

    @Disabled
    @Test
    public void testAdd()
    {
//        assertEquals(4,2+2);
    assertNotNull(userRepository.findByUserName("ram"));

    }

    @ParameterizedTest
    @CsvSource(
            {
                    "1,1,2",
                    "2,10,12",
                    "3,3,9"
            }
    )
    public void testAdd(int a ,int b,int expected)
    {
        assertEquals(expected,a+b);
    }

}















