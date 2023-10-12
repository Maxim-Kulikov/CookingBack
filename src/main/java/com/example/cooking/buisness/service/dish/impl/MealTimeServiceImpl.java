package com.example.cooking.buisness.service.dish.impl;

import com.example.cooking.presentation.dto.Query;
import com.example.cooking.presentation.dto.dish.req.CreateMealTimeReq;
import com.example.cooking.presentation.dto.dish.req.UpdateMealTimeReq;
import com.example.cooking.presentation.dto.dish.resp.DishTypeResp;
import com.example.cooking.presentation.dto.dish.resp.MealTimeResp;
import com.example.cooking.exception.dish.MealTimeIsExistedException;
import com.example.cooking.exception.dish.MealTimeNotFoundException;
import com.example.cooking.presentation.dto.mapper.dish.DishReqMapper;
import com.example.cooking.presentation.dto.mapper.dish.DishRespMapper;
import com.example.cooking.data.model.postgres.dish.MealTime;
import com.example.cooking.data.repository.postgres.dish.MealTimeRepository;
import com.example.cooking.buisness.service.dish.MealTimeService;
import com.example.cooking.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.cooking.util.DataValidator.isNullOrEmpty;

@Service
public class MealTimeServiceImpl implements MealTimeService {
    @Autowired
    private MealTimeRepository repository;

    @Autowired
    private DishReqMapper dishReqMapper;

    @Autowired
    private DishRespMapper dishRespMapper;

    @Transactional
    @Override
    public MealTimeResp save(CreateMealTimeReq req) {
        ifMealTimeIsExistedThrowException(req.getDayPart());
        MealTime mealTime = repository.save(dishReqMapper.toMealTime(req));
        return dishRespMapper.toMealTimeResp(mealTime);
    }

    @Transactional
    @Override
    public MealTimeResp update(Integer id, UpdateMealTimeReq req) {
        MealTime mealTime = getMealTimeOrThrowException(id);

        if (!isNullOrEmpty(req.getDayPart()) && !req.getDayPart().equals(mealTime.getDayPart())) {
            ifMealTimeIsExistedThrowException(req.getDayPart());
            mealTime.setDayPart(req.getDayPart());
        }

        repository.save(mealTime);

        return dishRespMapper.toMealTimeResp(mealTime);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        getMealTimeOrThrowException(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public MealTimeResp findById(Integer id) {
        return dishRespMapper.toMealTimeResp(getMealTimeOrThrowException(id));
    }

    @Transactional
    @Override
    public List<MealTimeResp> findAllByQuery(Query query) {
        return dishRespMapper.toMealTimeResps(DataValidator.isObjectOrFieldNull(query)
                ? (List<MealTime>) repository.findAll()
                : repository.findAllByDayPartContainsIgnoreCase(query.getQuery()));
    }

    @Transactional
    @Override
    public List<DishTypeResp> findAllDishTypesById(Integer id) {
        return dishRespMapper.toDishTypeResps(getMealTimeOrThrowException(id).getDishTypes());
    }

    protected MealTime getMealTimeOrThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new MealTimeNotFoundException(id));
    }

    protected void ifMealTimeIsExistedThrowException(String dayPart) {
        Optional<MealTime> mealTime = repository.findByDayPartIgnoreCase(dayPart);
        if (mealTime.isPresent()) {
            throw new MealTimeIsExistedException(mealTime.get().getId(), dayPart);
        }
    }

}
