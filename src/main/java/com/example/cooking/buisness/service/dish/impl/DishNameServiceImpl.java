package com.example.cooking.buisness.service.dish.impl;

import com.example.cooking.buisness.service.dish.DishNameService;
import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateDishNameReq;
import com.example.cooking.presentation.dto.dish.req.UpdateDishNameReq;
import com.example.cooking.presentation.dto.dish.resp.DishNameResp;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.exception.dish.DishNameIsExistedException;
import com.example.cooking.exception.dish.DishNameNotFoundException;
import com.example.cooking.presentation.dto.mapper.dish.DishReqMapper;
import com.example.cooking.presentation.dto.mapper.dish.DishRespMapper;
import com.example.cooking.data.model.mongo.RecipeInfo;
import com.example.cooking.data.model.postgres.dish.DishName;
import com.example.cooking.data.model.postgres.dish.DishType;
import com.example.cooking.data.repository.mongo.RecipeInfoRepository;
import com.example.cooking.data.repository.postgres.dish.DishNameRepository;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishNameServiceImpl implements DishNameService {
    @Autowired
    private DishNameRepository repository;

    @Autowired
    private RecipeInfoRepository recipeInfoRepository;

    @Autowired
    private DishTypeServiceImpl dishTypeService;

    @Autowired
    private DishRespMapper respMapper;

    @Autowired
    private DishReqMapper reqMapper;

    @Transactional
    @Override
    public DishNameResp save(CreateDishNameReq req) {
        ifDishNameIsExistedThrowException(req.getName(), req.getIdDishType());
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
            ifDishNameIsExistedThrowException(req.getName(), req.getIdDishType());
            dishName.setName(req.getName());
        }

        repository.save(dishName);

        return respMapper.toDishNameResp(dishName);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getDishNameOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public DishNameResp findById(Integer id) {
        return respMapper.toDishNameResp(getDishNameOrThrowException(id));
    }

    @Transactional
    @Override
    public List<DishNameResp> findAllByQuery(Query query) {
        return respMapper.toDishNameResps(DataValidator.isObjectOrFieldNull(query) ?
                (List<DishName>) repository.findAll() : repository.findAllByNameContainsIgnoreCase(query.getQuery()));
    }

    @Transactional
    @Override
    public List<DishResp> findAllDishesById(Integer id) {
        return getDishNameOrThrowException(id).getDishes()
                .stream()
                .map(dish -> respMapper.toDishResp(dish,
                        recipeInfoRepository
                                .findById(dish.getIdRecipeInfo())
                                .orElseGet(RecipeInfo::new)))
                .collect(Collectors.toList());
    }

    protected DishName getDishNameOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DishNameNotFoundException(id));
    }

    protected void ifDishNameIsExistedThrowException(String name, Integer idDishType) {
        Optional<DishName> dishName = repository.findFirstByNameIgnoreCaseAndDishTypeId(name, idDishType);
        if (dishName.isPresent()) {
            throw new DishNameIsExistedException(dishName.get().getId(), name);
        }
    }
}
