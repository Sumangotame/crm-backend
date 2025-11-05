package com.suman.crm.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String company;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String source;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(columnDefinition = "DATETIME(0)")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "DATETIME(0)")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Status {
        NEW, CONTACTED, QUALIFIED, CONVERTED, LOST
    }
}
