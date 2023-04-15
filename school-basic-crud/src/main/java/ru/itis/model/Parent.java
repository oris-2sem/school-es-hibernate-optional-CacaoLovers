package ru.itis.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "oris_hw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;

    @CreationTimestamp
    private Instant updatedAt;

    @Column
    private String name;
}
