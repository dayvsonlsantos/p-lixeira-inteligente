package com.example.j_api_iot.model.repository;

import com.example.j_api_iot.model.entity.Principal;

//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Integer> {


    List<Principal> findAll();


    @Query( value = " SELECT MAX(creation_time_stamp) AS ultima_data_de_criacao\n" +
            "FROM principal;", nativeQuery = true)
    String findByLastDate();


    @Query(value = "SELECT volume\n" +
            "FROM principal\n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    String findByVolumeNow();


    @Query(value = "SELECT rgb\n" +
            "FROM principal\n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    String findByColorNow();


    @Query(value = "SELECT COUNT(*) AS total_atualizacoes\n" +
            "FROM principal;", nativeQuery = true)
    Integer findByTotalUpdate();


    @Query(value = "SELECT COUNT(*) AS vezes_aberto\n" +
            "FROM principal\n" +
            "WHERE trash_state = 'open';", nativeQuery = true)
    Integer findByCountOpen();

    @Query(value = "SELECT COUNT(*) AS vezes_fechado\n" +
            "FROM principal\n" +
            "WHERE trash_state = 'close';", nativeQuery = true)
    Integer findByCountClose();


    @Query(value = "SELECT COUNT(*) AS vezes_laranja\n" +
            "FROM principal\n" +
            "WHERE rgb = '255,120,0';", nativeQuery = true)
    Integer findByCountColor();

    @Query(value = "SELECT MAX(p.creation_time_stamp) AS ultima_data_de_criacao, " +
            "       (SELECT p.volume FROM principal p ORDER BY p.id DESC LIMIT 1) AS volumeNow, " +
            "       (SELECT p.rgb FROM principal p ORDER BY p.id DESC LIMIT 1) AS colorNow, " +
            "       COUNT(*) AS total_atualizacoes, " +
            "       (SELECT COUNT(*) FROM principal p WHERE p.trash_state = 'open') AS vezesAberto, " +
            "       (SELECT COUNT(*) FROM principal p WHERE p.trash_state = 'close') AS vezesFechado, " +
            "       (SELECT COUNT(*) FROM principal p WHERE p.rgb = '255,120,0') AS vezesLaranja " +
            "FROM principal p", nativeQuery = true)
    List<Object> findByThisSQL();


}

