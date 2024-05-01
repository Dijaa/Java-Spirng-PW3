package com.example.projeto.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.models.DescontoModel;
import com.example.projeto.repository.DescontoRepository;

@Service
public class DescontoService {

    @Autowired
    private DescontoRepository repository;

    public DescontoModel insert(DescontoModel model) {
        return repository.save(model);
    }

    public List<DescontoModel> getAllValidDescontos() {
        List<Integer> ids = repository.findAllValidDescontos();
        System.out.println(ids);
        return ids.stream().map(id -> repository.findById(id).get()).collect(Collectors.toList());
    }
}
