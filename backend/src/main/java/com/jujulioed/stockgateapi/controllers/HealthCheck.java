package com.jujulioed.stockgateapi.controllers;

import com.jujulioed.stockgateapi.dtos.status.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/status")
public class HealthCheck {

    @GetMapping
    public StatusResponse pingApi() {
        return new StatusResponse(
                "Api Online e conectada"
        );
    }
}
