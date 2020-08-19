package se.lexicon.Jpa_Assignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private int price;

    //Constructor 1
    public Product(int id, String name, int price) {
        this.id = id;
        this.setName(name);
        this.setPrice(price);
    }

    //Constructor 2
    public Product(String name, int price) {
        this(0,name,price);
    }

    //Empty Constructor
    public Product() {}

    //Getters & Setters (sans SetID)
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    //Hashcode & Equals Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    //ToString Override
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
