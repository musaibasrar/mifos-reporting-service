package org.ideoholic.mrs.model;

import javax.persistence.*;

@Entity
@Table(name = "m_product_loan")
public class LoanProduct {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
