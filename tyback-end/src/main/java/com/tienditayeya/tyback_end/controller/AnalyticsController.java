package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.AnalyticsResponse;
import com.tienditayeya.tyback_end.service.AnalyticsService;
import com.tienditayeya.tyback_end.service.AuthSessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;
    private final AuthSessionService authSessionService;
    public AnalyticsController(AnalyticsService analyticsService,AuthSessionService authSessionService){this.analyticsService=analyticsService;this.authSessionService=authSessionService;}
    @GetMapping("/performance")
    public AnalyticsResponse performance(@RequestHeader(value="X-Session-Token",required=false) String token){
        authSessionService.requerirAdmin(token); return analyticsService.performance();
    }
}
