package org.ideoholic.mrs.model;

import javax.persistence.*;


@Entity
@Table(name = "m_staff")
public class LoanOfficer {

    @Id
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
