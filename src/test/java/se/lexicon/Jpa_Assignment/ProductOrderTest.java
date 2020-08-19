package se.lexicon.Jpa_Assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.Jpa_Assignment.entity.AppUser;
import se.lexicon.Jpa_Assignment.entity.OrderItem;
import se.lexicon.Jpa_Assignment.entity.Product;
import se.lexicon.Jpa_Assignment.entity.ProductOrder;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductOrderTest {
    private ProductOrder testProductOrder;
    private Product testProduct = new Product("Testbär", 42);
    private OrderItem testOrderItem = new OrderItem(12, testProduct);
    private LocalDateTime testLocalDateTime = LocalDateTime.of(2019, 11, 12, 12, 0);
    private HashSet<OrderItem> testProducts = new HashSet<>();
    private AppUser testCustomer = new AppUser("Ture", "Test", "ture.test@gmail.com");

    @BeforeEach
    public void createProductOrder() {
        testProductOrder = new ProductOrder(testLocalDateTime, testCustomer);
    }

    @AfterEach
    public void killProductOrder() {
        testProductOrder = null;
    }

    @Test
    public void testBeforeWorks() {
        //Arrange
        LocalDateTime expectedLDT = testLocalDateTime;
        HashSet<OrderItem> expectedProducts = testProducts;
        AppUser expectedCustomer = testCustomer;

        //Act
        //done by @BeforeEach

        //Assert
        assertTrue(testProductOrder.getId() > -1);
        ;
        assertEquals(expectedLDT, testLocalDateTime);
        assertEquals(expectedProducts, testProducts);
        assertEquals(expectedCustomer, testCustomer);
    }

    @Test
    public void testSetOrderDateTime() {
        //Arrange
        LocalDateTime orderTime = LocalDateTime.of(2019, 11, 13, 8, 0);

        //Act
        testProductOrder.setOrderDateTime(orderTime);

        //Assert
        assertEquals(orderTime, testProductOrder.getOrderDateTime());

    }

    @Test
    public void testSetCustomer() {
        //Arrange
        AppUser customer = new AppUser("Thora", "Test", "thora.test@hotmail.com");

        //Act
        testProductOrder.setCustomer(customer);

        //Assert
        assertEquals(customer, testProductOrder.getCustomer());
    }

    @Test
    public void testSetProduct() {
        //Arrange
        Product testProduct2 = new Product("Testpiller",56);
        OrderItem testOrderItem2 = new OrderItem(24,testProduct2);

        //Act
        testProductOrder.setProducts(testOrderItem2);

        //Assert
        assertTrue(testProductOrder.getProducts().contains(testOrderItem2));
    }

    @Test
    public void testToStringOverride() {
        //Act
        String result = testProductOrder.toString();

        //Assert
        assertTrue(result.contains(String.valueOf(testProductOrder.getOrderDateTime())));
        assertTrue(result.contains(String.valueOf(testProductOrder.getCustomer())));
    }

    @Test
    public void testEqualsAndHashCodeOverride() {
        //Arrange
        ProductOrder testProductOrder2 = new ProductOrder(testLocalDateTime, testCustomer);

        //Assert
        assertEquals(testProductOrder2, testProductOrder);
        assertEquals(testProductOrder2.hashCode(), testProductOrder.hashCode());
    }

    @Test
    public void testAddOrderItem() {
        //Act
        testProductOrder.addOrderItem(testOrderItem);

        //Assert
        assertTrue(testProductOrder.getProducts().contains(testOrderItem));
        assertEquals(testOrderItem.getProductOrder(), testProductOrder);
    }

    @Test
    public void testRemoveOrderItem() {
        //Arrange
        testProductOrder.addOrderItem(testOrderItem);

        //Act
        testProductOrder.removeOrderItem(testOrderItem);

        //Assert
        assertTrue(testProductOrder.getProducts().isEmpty());
        assertNull(testOrderItem.getProductOrder());
    }

    @Test
    public void testCalculateTotalOrderValue() {
        //Arrange
        double expectedresult = 1848;
        Product testProduct2 = new Product("Testpiller",56);
        OrderItem testOrderItem2 = new OrderItem(24,testProduct2);

        //Act
        testProductOrder.addOrderItem(testOrderItem);
        testProductOrder.addOrderItem(testOrderItem2);
        double actualresult = testProductOrder.calculateTotalOrderValue();

        //Assert
        assertEquals(expectedresult,actualresult,0);
    }
}

