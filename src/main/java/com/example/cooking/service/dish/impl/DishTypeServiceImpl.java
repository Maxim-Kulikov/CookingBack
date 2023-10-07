package com.example.cooking.service.dish.impl;

import com.example.cooking.dto.Query;
import com.example.cooking.dto.dish.req.CreateDishTypeReq;
import com.example.cooking.dto.dish.req.UpdateDishTypeReq;
import com.example.cooking.dto.dish.resp.DishNameResp;
import com.example.cooking.dto.dish.resp.DishTypeResp;
import com.example.cooking.exception.dish.DishTypeIsExistedException;
import com.example.cooking.exception.dish.DishTypeNotFoundException;
import com.example.cooking.mapper.dish.DishReqMapper;
import com.example.cooking.mapper.dish.DishRespMapper;
import com.example.cooking.model.postgres.dish.DishType;
import com.example.cooking.model.postgres.dish.MealTime;
import com.example.cooking.repository.postgres.dish.DishTypeRepository;
import com.example.cooking.service.dish.DishTypeService;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DishTypeServiceImpl implements DishTypeService {
    @Autowired
    private MealTimeServiceImpl mealTimeService;

    @Autowired
    private DishTypeRepository dishTypeRepository;

    @Autowired
    private DishReqMapper dishReqMapper;

    @Autowired
    private DishRespMapper dishRespMapper;

    @Override
    @Transactional
    public DishTypeResp save(CreateDishTypeReq req) {
        ifDishTypeIsExistedThrowException(req.getType());
        MealTime mealTime = mealTimeService.getMealTimeOrThrowException(req.getIdMealTime());
        DishType dishType = dishTypeRepository.save(dishReqMapper.toDishType(req, mealTime));

        return dishRespMapper.toDishTypeResp(dishType);
    }

    @Override
    @Transactional
    public DishTypeResp update(Integer id, UpdateDishTypeReq req) {
        DishType dishType = getDishTypeOrThrowException(id);

        if (req.getIdMealTime() != null && !req.getIdMealTime().equals(dishType.getMealTime().getId())) {
            MealTime mealTime = mealTimeService.getMealTimeOrThrowException(req.getIdMealTime());
            dishType.setMealTime(mealTime);
        }

        if (!DataValidator.isNullOrEmpty(req.getType()) && !req.getType().equals(dishType.getType())) {
            ifDishTypeIsExistedThrowException(req.getType());
            dishType.setType(req.getType());
        }

        dishTypeRepository.save(dishType);

        return dishRespMapper.toDishTypeResp(dishType);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        getDishTypeOrThrowException(id);
        dishTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DishTypeResp findById(Integer id) {
        return dishRespMapper.toDishTypeResp(getDishTypeOrThrowException(id));
    }

    @Override
    @Transactional
    public List<DishTypeResp> findAllByQuery(Query query) {
        return dishRespMapper.toDishTypeResps(DataValidator.isObjectOrFieldNull(query)
                ? (List<DishType>) dishTypeRepository.findAll()
                : dishTypeRepository.findAllByTypeContainsIgnoreCase(query.getQuery())
        );
    }

    @Override
    @Transactional
    public List<DishNameResp> findAllDishNamesById(Integer id) {
        return dishRespMapper.toDishNameResps(getDishTypeOrThrowException(id).getDishNames());
    }

    protected DishType getDishTypeOrThrowException(int id) {
        return dishTypeRepository.findById(id)
                .orElseThrow(() -> new DishTypeNotFoundException(id));
    }

    protected void ifDishTypeIsExistedThrowException(String type) {
        Optional<DishType> dishType = dishTypeRepository.findFirstByType(type);
        if (dishType.isPresent()) {
            throw new DishTypeIsExistedException(dishType.get().getId(), type);
        }
    }

}
