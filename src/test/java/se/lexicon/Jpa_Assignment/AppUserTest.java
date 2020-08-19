package se.lexicon.Jpa_Assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.Jpa_Assignment.entity.AppUser;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppUserTest {
    private AppUser testUser;
    private String testFirstName = "Ture";
    private String testLastName = "Test";
    private String testEmail = "ture.test@gmail.com";

    @BeforeEach
    public void createUser() {
        testUser = new AppUser(testFirstName,testLastName,testEmail);
    }

    @AfterEach
    public void killUser() {
        testUser = null;
    }

    @Test
    public void testBeforeWorks() {
        //Arrange
        String expectedFirstName = testFirstName;
        String expectedLastName = testLastName;
        String expectedEmail = testEmail;

        //Act
        //done by @BeforeEach

        //Assert
        assertTrue(testUser.getId() > -1);
        assertEquals(expectedFirstName, testUser.getFirstName());
        assertEquals(expectedLastName, testUser.getLastName());
        assertEquals(expectedEmail, testUser.getEmail());
    }

    @Test
    public void testSetFirstName() {
        //Arrange
        String userFirstName = "Boaty";

        //Act
        testUser.setFirstName(userFirstName);

        //Assert
        assertEquals(userFirstName, testUser.getFirstName());
    }

    @Test
    public void testSetLastName() {
        //Arrange
        String userLastName = "Mc Boatface";

        //Act
        testUser.setLastName(userLastName);

        //Assert
        assertEquals(userLastName, testUser.getLastName());
    }

    @Test
    public void testSetEmail() {
        //Arrange
        String userEmail = "boaty.mcboatface@hotmale.com";

        //Act
        testUser.setEmail(userEmail);

        //Assert
        assertEquals(userEmail, testUser.getEmail());
    }

    @Test
    public void testToString() {

        //Act
        String result = testUser.toString();

        //Assert
        assertTrue(result.contains(testEmail));
        assertTrue((result.contains(testLastName)));
        assertTrue(result.contains(testFirstName));
        assertTrue(result.contains(String.valueOf(testUser.getId())));

    }

    @Test void testEqualsAndHashCode() {
        //Arrange
        AppUser testUser2 = new AppUser(testFirstName,testLastName,testEmail);

        //Assert
        assertEquals(testUser2, testUser);
        assertEquals(testUser2.hashCode(),testUser.hashCode());
    }


}
