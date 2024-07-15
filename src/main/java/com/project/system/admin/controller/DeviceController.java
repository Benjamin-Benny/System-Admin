package com.project.system.admin.controller;

import com.project.system.admin.model.Applications;
import com.project.system.admin.model.Device;
import com.project.system.admin.model.DeviceApplication;
import com.project.system.admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public List<Device> getDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/applications")
    public List<Applications> getApplications() {
        return deviceService.getAllApplications();
    }

    @GetMapping("/device-applications")
    public String getDeviceApplications(@RequestParam(required = false) String sortBy,
                                        @RequestParam(required = false) String order,
                                        Model model) {
        List<DeviceApplication> deviceApplications = deviceService.getAllDeviceApplications(sortBy, order);
        model.addAttribute("deviceApplications", deviceApplications);
        return "device-applications";
    }

    @PostMapping("/device-applications/{id}/update")
    public DeviceApplication updateDeviceApplication(@PathVariable Long id) {
        return deviceService.updateDeviceApplication(id);
    }

    @GetMapping("/applications/{name}/latest")
    public Applications getLatestApplicationVersion(@PathVariable String name) {
        return deviceService.getLatestApplicationVersion(name);
    }
}
