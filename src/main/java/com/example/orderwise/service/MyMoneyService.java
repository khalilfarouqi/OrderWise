package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.MyMoneyDto;
import com.example.orderwise.common.dto.WalletDto;
import com.example.orderwise.entity.MyMoney;
import com.example.orderwise.entity.User;
import com.example.orderwise.entity.enums.EtatDemande;
import com.example.orderwise.exception.BusinessException;
import com.example.orderwise.repository.MyMoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MyMoneyService implements IBaseService<MyMoney, MyMoneyDto> {
    private final MyMoneyRepository myMoneyRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;
    private final WalletService walletService;

    @Override
    @Transactional
    public MyMoneyDto save(MyMoneyDto dto) {
        MyMoney myMoney = modelMapper.map(dto, MyMoney.class);
        WalletDto walletDto = walletService.getWalletBySeller(dto.getUser().getUsername());

        double totalPendingAmount = findByUserUsername(dto.getUser().getUsername()).stream()
                .filter(myMoneyDto -> myMoneyDto.getEtatDemande() == EtatDemande.ENCOURS)
                .mapToDouble(MyMoneyDto::getMontant)
                .sum();

        if (walletDto.getAmountCredited() < dto.getMontant())
            throw new BusinessException("Vous pouvez pas demandée ce montant.");
        else if (totalPendingAmount + dto.getMontant() > walletDto.getAmountCredited())
            throw new BusinessException("Vous pouvez pas demandée ce montant car vous avez déja des demandes non traitée.");

        myMoney.setUser(modelMapper.map(userService.findByUsername(dto.getUser().getUsername()), User.class));
        myMoney.setDateDeDemande(new Date());
        myMoney.setEtatDemande(EtatDemande.ENCOURS);
        return modelMapper.map(myMoneyRepository.save(myMoney), MyMoneyDto.class);
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

    public List<MyMoneyDto> findByUserUsername(String username) {
        return myMoneyRepository.findByUserUsername(username).stream().map(myMoney -> modelMapper.map(myMoney, MyMoneyDto.class)).toList();
    }

    @Override
    public List<MyMoneyDto> findAll() {
        return myMoneyRepository.findAll()
                .stream()
                .map(money -> modelMapper.map(money, MyMoneyDto.class))
                .toList();
    }

    @Override
    public Page<MyMoneyDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
