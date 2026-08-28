package com.tienditayeya.tyback_end.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    private final JdbcTemplate jdbcTemplate;
    public HealthController(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @GetMapping
    public Map<String,Object> health(){
        Integer db = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return Map.of("status","ok","database", db != null && db == 1 ? "ok" : "error", "timestamp", Instant.now().toString());
    }
}
