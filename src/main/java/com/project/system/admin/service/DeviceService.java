package com.project.system.admin.service;

import com.project.system.admin.model.Applications;
import com.project.system.admin.model.Device;
import com.project.system.admin.model.DeviceApplication;
import com.project.system.admin.repository.ApplicationRepository;
import com.project.system.admin.repository.DeviceApplicationRepository;
import com.project.system.admin.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private DeviceApplicationRepository deviceApplicationRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Applications> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<DeviceApplication> getAllDeviceApplications(String sortBy, String order) {
        List<DeviceApplication> deviceApplications = deviceApplicationRepository.findAll();

        if (sortBy != null) {
            Comparator<DeviceApplication> comparator;
            switch (sortBy) {
                case "device":
                    comparator = Comparator.comparing(da -> da.getDevice().getName());
                    break;
                case "application":
                    comparator = Comparator.comparing(da -> da.getApplications().getName());
                    break;
                case "currentVersion":
                    comparator = Comparator.comparing(DeviceApplication::getCurrentVersion);
                    break;
                case "lastUpdated":
                    comparator = Comparator.comparing(DeviceApplication::getLastUpdated);
                    break;
                case "status":
                    comparator = Comparator.comparing(DeviceApplication::getStatus);
                    break;
                default:
                    comparator = Comparator.comparing(DeviceApplication::getId); // default sorting
            }
            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            deviceApplications = deviceApplications.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }
        return deviceApplications;
    }

    public DeviceApplication updateDeviceApplication(Long deviceApplicationId) {
        DeviceApplication deviceApplication = deviceApplicationRepository.findById(deviceApplicationId)
                .orElseThrow(() -> new RuntimeException("DeviceApplication not found"));

        Applications application = deviceApplication.getApplications();
        deviceApplication.setCurrentVersion(application.getLatestVersion());
        deviceApplication.setStatus("Updated");
        deviceApplication.setLastUpdated(LocalDateTime.now());

        return deviceApplicationRepository.save(deviceApplication);
    }

    public Applications getLatestApplicationVersion(String appName) {
        return applicationRepository.findByName(appName)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }
}
