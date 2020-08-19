package se.lexicon.Jpa_Assignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderItem {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @ManyToOne (
            fetch = FetchType.EAGER )
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne (
            fetch = FetchType.LAZY )
    @JoinColumn(name = "order_id")
    private ProductOrder productOrder;


    //Constructor 1
    public OrderItem(int id, int quantity, Product product) {
        this.id = id;
        this.setQuantity(quantity);
        this.setProduct(product);
    }

    //Constructor 2
    public OrderItem(int quantity, Product product) {
        this(0,quantity,product);
    }

    //Empty Constructor
    public OrderItem() {}

    //Getters & Setters (sans SetID)
    public int getId() { return id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public ProductOrder getProductOrder() { return productOrder; }
    public void setProductOrder(ProductOrder productOrder) { this.productOrder = productOrder; }

    //Method to calculate Price
    public int calculateOrderPrice() {
        int result;
        result = (this.getProduct().getPrice() * this.getQuantity());
        return  result;
    }

    //Equals & Hashcode Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                quantity == orderItem.quantity &&
                product.equals(orderItem.product);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product);
    }

    //ToString Override
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
