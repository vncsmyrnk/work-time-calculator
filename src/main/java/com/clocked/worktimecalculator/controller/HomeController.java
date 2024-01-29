package com.clocked.worktimecalculator.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
  @GetMapping(value = "")
  public Map<String, String> home() {
    HashMap<String, String> map = new HashMap<>();
    map.put("name", "Work Time Calculator");
    map.put("version", System.getenv().getOrDefault("WTC_VERSION", "v0.0.0"));
    return map;
  }
}
