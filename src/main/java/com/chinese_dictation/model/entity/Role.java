package com.chinese_dictation.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private com.chinese_dictation.model.enums.Role name = com.chinese_dictation.model.enums.Role.USER;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<User> users;
}
