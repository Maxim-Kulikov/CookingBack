package com.example.cooking.service;

import com.example.cooking.dto.Query;

import java.util.List;

public interface MainService<Save, Update, Resp> {
    Resp save(Save req);

    Resp update(Integer id, Update req);

    void delete(Integer id);

    Resp findById(Integer id);

    List<Resp> findAllByQuery(Query query);
}
