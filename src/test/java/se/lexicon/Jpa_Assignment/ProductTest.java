package se.lexicon.Jpa_Assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Jpa_Assignment.entity.Product;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {
    private Product testProduct;
    private int testId;
    private String testName = "Testbär";
    private int testPrice = 42;

    @BeforeEach
    public void createProduct() {
        testProduct = new Product(testName,testPrice);
    }

    @AfterEach
    public void killUser() {
        testProduct = null;
    }

    @Test
    public void testBeforeWorks() {
        //Arrange
        String expectedName = testName;
        int expectedPrice = testPrice;

        //Act
        //done by @BeforeEach

        //Assert
        assertTrue(testProduct.getId() > -1);
        assertEquals(expectedName, testProduct.getName());
        assertEquals(expectedPrice,testProduct.getPrice());
    }

    @Test
    public void testSetName() {
        //Arrange
        String productName = "Testjuice";

        //Act
        testProduct.setName(productName);

        //Assert
        assertEquals(productName, testProduct.getName());
    }

    @Test
    public void testSetPrice() {
        //Arrange
        int productPrice = 69;

        //Act
        testProduct.setPrice(productPrice);

        //Assert
        assertEquals(productPrice, testProduct.getPrice());
    }

    @Test
    public void testToString() {

        //Act
        String result = testProduct.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testProduct.getPrice())));
        assertTrue(result.contains(testName));
        assertTrue(result.contains(String.valueOf(testProduct.getId())));
    }

    @Test void testEqualsAndHashCode() {
        //Arrange
        Product testProduct2 = new Product(testName,testPrice);

        //Assert
        assertEquals(testProduct2, testProduct);
        assertEquals(testProduct2.hashCode(),testProduct.hashCode());
    }
}
