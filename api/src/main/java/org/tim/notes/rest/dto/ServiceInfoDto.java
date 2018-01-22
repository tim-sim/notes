package org.tim.notes.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServiceInfoDto {
    private String name;
    private String version;
}
