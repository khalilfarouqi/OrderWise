package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.MyMoneyDto;
import com.example.orderwise.entity.MyMoney;
import com.example.orderwise.repository.MyMoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MyMoneyService implements IBaseService<MyMoney, MyMoneyDto> {
    private final MyMoneyRepository myMoneyRepository;
    private final ModelMapper modelMapper;

    @Override
    public MyMoneyDto save(MyMoneyDto dto) {
        return null;
    }

    @Override
    public MyMoneyDto update(MyMoneyDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MyMoneyDto findById(Long id) {
        return null;
    }

    public MyMoneyDto findByUserUsername(String username) {
        Optional<MyMoney> myMoney = myMoneyRepository.findByUserUsername(username);
        return myMoney.map(money -> modelMapper.map(money, MyMoneyDto.class)).orElse(null);
    }

    @Override
    public List<MyMoneyDto> findAll() {
        return List.of();
    }

    @Override
    public Page<MyMoneyDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
