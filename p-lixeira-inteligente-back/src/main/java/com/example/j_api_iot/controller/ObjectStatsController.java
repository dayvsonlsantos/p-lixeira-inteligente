package com.example.j_api_iot.controller;

import com.example.j_api_iot.model.entity.PrincipalStats;
import com.example.j_api_iot.model.repository.PrincipalRepository;
import com.example.j_api_iot.model.repository.PrincipalStatsRepository;
import com.example.j_api_iot.model.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ob/")
public class ObjectStatsController {

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private PrincipalStatsRepository principalStatsRepository;

    private PrincipalRepository repository;

    public ObjectStatsController(PrincipalRepository repository,
                                 PrincipalService principalService,
                                 PrincipalStatsRepository principalStatsRepository1) {
        this.repository = repository;
        this.principalService = principalService;
    }

    //@CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("principal_stats")
    public List<PrincipalStats> allPrincipalStats(PrincipalStats principalStats) {
        principalService.salvandoEm();
        return principalService.findAllPrincipalStats();
    }

}
