package com.example.j_api_iot.controller;

import com.example.j_api_iot.model.dto.ObjectStastsDTO;
import com.example.j_api_iot.model.entity.PrincipalStats;
import com.example.j_api_iot.model.entity.Principal;
import com.example.j_api_iot.model.repository.PrincipalRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/")
public class PrincipalController {

    private  PrincipalRepository repository;

    public PrincipalController(PrincipalRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public Principal salvar(@RequestBody Principal principal){

    return repository.save(principal);

    }


    @GetMapping
    public List<Principal> findAll(Principal principal){

        return repository.findAll();

    }

    @GetMapping("lastTime")
    public String lastDate(Principal principal){

        return repository.findByLastDate();

    }

    @GetMapping("volumeNow")
    public String volumeNow(Principal principal){

        return repository.findByVolumeNow();

    }

    @GetMapping("colorNow")
    public Object colorNow(Principal principal){

        return repository.findByColorNow();

    }

    @GetMapping("totalUpdate")
    public Integer totalUpdate(Principal principal){

        return repository.findByTotalUpdate();

    }

    @GetMapping("countOpen")
    public Integer countOpen(Principal principal){

        return repository.findByCountOpen() ;

    }

    @GetMapping("countClose")
    public Integer countClose(Principal principal){

        return repository.findByCountClose() ;

    }

    @GetMapping("countColor")
    public Integer countColor(Principal principal){

        return repository.findByCountColor() ;

    }


    @GetMapping("findSql")
    public Object findSql(PrincipalStats dto){

        return repository.findByThisSQL();

    }

}
