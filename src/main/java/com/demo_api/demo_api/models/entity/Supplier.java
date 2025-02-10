package com.demo_api.demo_api.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Set;

@Table(name = "tbl_suppliers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Supplier implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=150, nullable=false)
    private String name;

    @Column(length=150, nullable=false)
    private String address;

    @Column(length=150, nullable=false, unique=true)
    private String email;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products;

}
