package com.example.j_api_iot.model.repository;

import com.example.j_api_iot.model.entity.PrincipalStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrincipalStatsRepository extends JpaRepository<PrincipalStats,Integer > {


   List<PrincipalStats> findAll();

//    @Query(value = "SELECT MAX(p.creation_time_stamp) AS ultima_data_de_criacao, " +
//            "       (SELECT p.volume FROM principal p ORDER BY p.id DESC LIMIT 1) AS volumeNow, " +
//            "       (SELECT p.rgb FROM principal p ORDER BY p.id DESC LIMIT 1) AS colorNow, " +
//            "       COUNT(*) AS total_atualizacoes, " +
//            "       (SELECT COUNT(*) FROM principal p WHERE p.trash_state = 'open') AS vezesAberto, " +
//            "       (SELECT COUNT(*) FROM principal p WHERE p.trash_state = 'close') AS vezesFechado, " +
//            "       (SELECT COUNT(*) FROM principal p WHERE p.rgb = '255,120,0') AS vezesLaranja " +
//            "FROM principal p", nativeQuery = true)
//    List<Object> findIt();
}
