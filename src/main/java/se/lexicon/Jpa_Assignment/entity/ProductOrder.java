package se.lexicon.Jpa_Assignment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductOrder {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime orderDateTime;
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "productOrder",
            orphanRemoval = true)

    private Set<OrderItem> products;
    @ManyToOne
    private AppUser customer;

    //Constructor 1
    public ProductOrder(int id, LocalDateTime orderDateTime, AppUser customer) {
        this.id = id;
        this.setOrderDateTime(orderDateTime);
        this.products = new HashSet<>();
        this.setCustomer(customer);
    }

    //Constructor 2
    public ProductOrder(LocalDateTime orderDateTime, AppUser customer) {
        this(0,orderDateTime,customer);
    }

    //Empty Constructor
    public ProductOrder() {
    }

    //Getters & Setters
    public int getId() { return id; }
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }
    public Set<OrderItem> getProducts() { return products; }
    public void setProducts(OrderItem orderitem) {this.products.add(orderitem);}
    public AppUser getCustomer() { return customer; }
    public void setCustomer(AppUser customer) { this.customer = customer; }

    //Equals & Hashcode ovverride
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return id == that.id &&
                orderDateTime.equals(that.orderDateTime) &&
                customer.equals(that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDateTime, customer);
    }

    //ToString override
    @Override
    public String toString() {
        return "ProductOrder{" +
                "productorder_id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", customer=" + customer +
                '}';
    }

    public void addOrderItem(OrderItem orderItem) {
        this.products.add(orderItem);
        orderItem.setProductOrder(this);
    }


    public void removeOrderItem(OrderItem orderItem) {
        this.products.remove(orderItem);
        orderItem.setProductOrder(null);
    }

    public double calculateTotalOrderValue() {
        double ordervalue = products.stream()
                .mapToDouble(OrderItem::calculateOrderPrice)
                .sum();
        return ordervalue;
    }
}

