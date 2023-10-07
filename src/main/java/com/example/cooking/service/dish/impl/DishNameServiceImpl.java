package com.example.cooking.service.dish.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.dish.req.CreateDishNameReq;
import com.example.cooking.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishResp;
import com.example.cooking.exception.dish.DishNameIsExistedException;
import com.example.cooking.exception.dish.DishNameNotFoundException;
import com.example.cooking.mapper.dish.DishReqMapper;
import com.example.cooking.mapper.dish.DishRespMapper;
import com.example.cooking.model.postgres.dish.DishName;
import com.example.cooking.model.postgres.dish.DishType;
import com.example.cooking.repository.postgres.dish.DishNameRepository;
import com.example.cooking.service.dish.DishNameService;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DishNameServiceImpl implements DishNameService {
    @Autowired
    private DishNameRepository repository;

    @Autowired
    private DishTypeServiceImpl dishTypeService;

    @Autowired
    private DishRespMapper respMapper;

    @Autowired
    private DishReqMapper reqMapper;

    @Transactional
    @Override
    public DishNameResp save(CreateDishNameReq req) {
        ifDishNameIsExistedThrowException(req.getName());
        DishType dishType = dishTypeService.getDishTypeOrThrowException(req.getIdDishType());
        DishName dishName = reqMapper.toDishName(req, dishType);
        repository.save(dishName);
        return respMapper.toDishNameResp(dishName);
    }

    @Transactional
    @Override
    public DishNameResp update(Integer id, UpdateDishNameReq req) {
        DishName dishName = getDishNameOrThrowException(id);

        if (req.getIdDishType() != null && !req.getIdDishType().equals(dishName.getDishType().getId())) {
            DishType dishType = dishTypeService.getDishTypeOrThrowException(req.getIdDishType());
            dishName.setDishType(dishType);
        }

        if (!DataValidator.isNullOrEmpty(req.getName()) && !req.getName().equals(dishName.getName())) {
            ifDishNameIsExistedThrowException(req.getName());
            dishName.setName(req.getName());
        }

        repository.save(dishName);

        return respMapper.toDishNameResp(dishName);
    }

    @Override
    public void delete(Integer id) {
        getDishNameOrThrowException(id);
        repository.deleteById(id);
    }

    @Override
    public DishNameResp findById(Integer id) {
        return respMapper.toDishNameResp(getDishNameOrThrowException(id));
    }

    @Override
    public List<DishNameResp> findAllByQuery(Query query) {
        return respMapper.toDishNameResps(DataValidator.isObjectOrFieldNull(query) ?
                (List<DishName>) repository.findAll() : repository.findAllByNameContainsIgnoreCase(query.getQuery()));
    }

    @Override
    public List<DishResp> findAllDishesById(Integer id) {
        return respMapper.toDishResps(getDishNameOrThrowException(id).getDishes());
    }

    protected DishName getDishNameOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DishNameNotFoundException(id));
    }

    protected void ifDishNameIsExistedThrowException(String name) {
        Optional<DishName> dishName = repository.findFirstByName(name);
        if (dishName.isPresent()) {
            throw new DishNameIsExistedException(dishName.get().getId(), name);
        }
    }
}
