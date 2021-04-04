package ru.mirea.practice21.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
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
