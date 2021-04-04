package ru.mirea.practice24.models;

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

    private String name;

    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "market")
    public Set<Product> products;
}
