package com.example.projeto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projeto.models.DescontoModel;

public interface DescontoRepository extends JpaRepository<DescontoModel, Integer> {

    @Query(value = "SELECT id FROM descontos WHERE STR_TO_DATE(data_limite, '%d/%m/%Y') > CURDATE() OR data_limite is NULL", nativeQuery = true)
    List<Integer> findAllValidDescontos();
}
