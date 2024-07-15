package com.project.system.admin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeviceApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Applications applications;

    private String currentVersion;
    private String status;
    private LocalDateTime lastUpdated;

    // Getters and setters
}
