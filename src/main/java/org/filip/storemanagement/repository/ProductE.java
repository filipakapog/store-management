package org.filip.storemanagement.repository;

import jakarta.persistence.*;
import org.filip.storemanagement.service.Product;

import java.time.Instant;
import java.util.Date;

@Entity
public class ProductE {

    @Id
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    private String name;
    private String description;
    private Integer quantity;
    private Double price;

    public ProductE() {

    }

    public ProductE(Product product) {
        Date now = Date.from(Instant.now());
        setCreatedAt(now);
        setUpdatedAt(now);
        setCreatedBy("me");
        setUpdatedBy("me");

        setId(product.getId().toString());
        setName(product.getName());
        setDescription(product.getDescription());
        setQuantity(product.getQuantity());
        setPrice(product.getPrice());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
