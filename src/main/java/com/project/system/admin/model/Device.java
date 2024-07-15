package com.project.system.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DeviceApplication> deviceApplications;

    // Getters and setters
}
