package ru.mirea.practice17.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="markets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "market", fetch = FetchType.LAZY)
    public Set<Product> products;
}
