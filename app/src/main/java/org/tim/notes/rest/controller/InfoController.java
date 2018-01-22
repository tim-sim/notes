package org.tim.notes.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tim.notes.rest.dto.ServiceInfoDto;

@RestController
public class InfoController {
    @Value("${service.name}")
    private String name;
    @Value("${service.version}")
    private String version;

    @GetMapping(path = "/_info")
    public ServiceInfoDto getServiceInfo() {
        return ServiceInfoDto.builder()
                .name(name)
                .version(version)
                .build();
    }
}
