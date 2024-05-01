package com.example.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projeto.models.ImovelModel;

public interface ImovelRepository extends JpaRepository<ImovelModel, Integer> {

}