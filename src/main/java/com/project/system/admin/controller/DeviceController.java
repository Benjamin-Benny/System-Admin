package com.project.system.admin.controller;

import com.project.system.admin.model.Applications;
import com.project.system.admin.model.Device;
import com.project.system.admin.model.DeviceApplication;
import com.project.system.admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

        System.out.println("Sorting by: " + sortBy + " in " + order + " order");

        List<DeviceApplication> deviceApplications = deviceService.getAllDeviceApplications(sortBy, order);
        model.addAttribute("deviceApplications", deviceApplications);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "device-applications";
    }

    @GetMapping("/test")
    public String printTest(Model model) {
        model.addAttribute("message", "Hello, this is a sample message!");
        return "test-page";
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
