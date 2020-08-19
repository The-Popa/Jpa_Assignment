package se.lexicon.Jpa_Assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.Jpa_Assignment.entity.OrderItem;
import se.lexicon.Jpa_Assignment.entity.Product;
import se.lexicon.Jpa_Assignment.entity.ProductOrder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderItemTest {
    private OrderItem testOrderItem;
    private int testQuantity = 12;
    private Product testProduct = new Product(0,"Testbär",42);
    private ProductOrder testProductOrder;

    @BeforeEach
    public void createProduct() {
        testOrderItem = new OrderItem(testQuantity,testProduct);
    }

    @AfterEach
    public void killUser() {
        testOrderItem = null;
    }

    @Test
    public void testBeforeWorks() {
        //Arrange
        int expectedQuantity = testQuantity;
        Product expectedProduct = testProduct;
        ProductOrder expectedProductOrder = testProductOrder;

        //Act
        //done by @BeforeEach

        //Assert
        assertTrue(testOrderItem.getId() > -1);
        assertEquals(expectedQuantity, testOrderItem.getQuantity());
        assertEquals(expectedProduct, testProduct);
        assertEquals(expectedProductOrder, testProductOrder);
    }

    @Test
    public void testSetQuantity() {
        //Arrange
        int orderItemQuantity = 24;

        //Act
        testOrderItem.setQuantity(orderItemQuantity);

        //Assert
        assertEquals(orderItemQuantity, testOrderItem.getQuantity());
    }

    @Test
    public void testSetProduct() {
        //Arrange
        Product testProduct2 = new Product(1,"Testsaker",45);

        //Act
        testOrderItem.setProduct(testProduct2);

        //Assert
        assertEquals(testProduct2, testOrderItem.getProduct());
    }

    @Test
    public void testCalculateOrderPrice() {
        //Arrange
        int expectedOrderPrice = 504;

        //Act
        int actualResult = testOrderItem.calculateOrderPrice();

        //Assert
        assertEquals(expectedOrderPrice,actualResult);
    }

    @Test
    public void testToString() {

        //Act
        String result = testOrderItem.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testOrderItem.getQuantity())));
        assertTrue(result.contains(String.valueOf(testOrderItem.getProduct())));

    }

    @Test void testEqualsAndHashCode() {
        //Arrange
        OrderItem testOrderItem2 = new OrderItem(testQuantity,testProduct);

        //Assert
        assertEquals(testOrderItem2,testOrderItem);
        assertEquals(testOrderItem2.hashCode(),testOrderItem.hashCode());
    }

}
