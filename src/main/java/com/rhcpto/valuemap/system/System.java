package com.rhcpto.valuemap.system;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class System {
    
    private Integer systemId;
    
    @NotEmpty
    private String name;

}