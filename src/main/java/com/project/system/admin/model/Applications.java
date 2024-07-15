package com.project.system.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String latestVersion;

    @OneToMany(mappedBy = "applications", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceApplication> deviceApplications;

    // Getters and setters
}
