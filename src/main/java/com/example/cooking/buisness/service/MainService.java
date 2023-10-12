package com.example.cooking.buisness.service;

import com.example.cooking.presentation.dto.Query;

import java.util.List;

public interface MainService<Save, Update, Resp> {
    Resp save(Save req);

    Resp update(Integer id, Update req);

    void delete(Integer id);

    Resp findById(Integer id);

    List<Resp> findAllByQuery(Query query);

}
