package com.leeroot.springboot.exception.handler.dto;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


public class Dto {
    @Getter
    @Setter
    public static class getController{
        @NotNull(message = "name")
        String name;
    }
    @Getter
    @Setter
    public static class postController{
        @NotNull(message = "name")
        String name;
    }
}
