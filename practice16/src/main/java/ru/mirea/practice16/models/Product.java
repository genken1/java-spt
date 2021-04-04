package ru.mirea.practice16.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private int price;

    @Column(name = "market_id")
    private int marketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="market_id", insertable = false, updatable = false)
    @JsonIgnore
    public Market market;
}
