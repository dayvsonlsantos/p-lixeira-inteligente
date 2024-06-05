package com.example.j_api_iot.model.service;

import com.example.j_api_iot.model.entity.PrincipalStats;
import com.example.j_api_iot.model.repository.PrincipalRepository;
import com.example.j_api_iot.model.repository.PrincipalStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class PrincipalService {

    @Autowired
    private PrincipalRepository principalRepository;

    @Autowired
    private PrincipalStatsRepository statusRepository;

    public PrincipalStats salvandoEm() {
//        List<Object[]> results = principalRepository.findPrincipalStatsRaw();
//
//        if (results.isEmpty()) {
//            return null; // ou você pode retornar um objeto vazio ou lançar uma exceção
//        }

//        Object[] row = results.get(0);
//
//        Instant date = row[0] != null ? ((Timestamp) row[0]).toInstant() : null;
//        Object volume = row[1];
//        Object rgb = row[2];
//        Integer quantUpdates = ((Number) row[3]).intValue();
//        Integer quantOpen = ((Number) row[4]).intValue();
//        Integer quantClose = ((Number) row[5]).intValue();
//        Integer quantOrangeLevel = ((Number) row[6]).intValue();

        // Criar um novo PrincipalStats e salvar no repositório
        PrincipalStats principalStats = new PrincipalStats();
        principalStats.setTempo(principalRepository.findByLastDate());
        principalStats.setVolume(principalRepository.findByVolumeNow());
        principalStats.setRgb(principalRepository.findByColorNow());
        principalStats.setQuant_updates(principalRepository.findByTotalUpdate());
        principalStats.setQuant_open(principalRepository.findByCountOpen());
        principalStats.setQuant_close(principalRepository.findByCountClose());
        principalStats.setQuant_orange_level(principalRepository.findByCountColor());

        statusRepository.save(principalStats);

        return principalStats;
    }

    // Método para obter todos os registros da tabela principal_stats
    public List<PrincipalStats> findAllPrincipalStats() {
        return statusRepository.findAll();
    }

}
