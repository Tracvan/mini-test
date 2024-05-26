package com.codegym.minitest.model;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank (message = "Code không được để trống")
    @Pattern( regexp ="^CG.{6}$", message = "Code phải bắt đầu băng CG và có 8 ký tự")
    @Column(unique = true)
    private String code;
    @NotBlank (message = "Không được để trống")
    private String destination;
    @Min(value = 1, message = "Giá không được bé hơn 1 ")
    @Max(value = 100000, message = "Giá không được lớn hơn 100.000")
    private double price;
    private String userId;
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;

    public Tour() {
    }

    public Tour(Long id, String code, String destination, double price, String userId, Type type) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.userId = userId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
