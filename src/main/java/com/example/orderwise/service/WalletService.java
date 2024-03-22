package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.WalletDto;
import com.example.orderwise.entity.Wallet;
import com.example.orderwise.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WalletService implements IBaseService<Wallet, WalletDto> {
    private final WalletRepository walletRepository;
    private ModelMapper modelMapper;
    @Override
    public WalletDto save(WalletDto dto) {
        return modelMapper.map(walletRepository.save(modelMapper.map(dto, Wallet.class)), WalletDto.class);
    }

    @Override
    public WalletDto update(WalletDto dto) {
        return modelMapper.map(walletRepository.save(modelMapper.map(dto, Wallet.class)), WalletDto.class);
    }

    @Override
    public void delete(Long id) {
        walletRepository.deleteById(id);
    }

    @Override
    public WalletDto findById(Long id) {
        return modelMapper.map(walletRepository.findById(id), WalletDto.class);
    }

    @Override
    public List<WalletDto> findAll() {
        return walletRepository.findAll()
                .stream()
                .map(wallet -> modelMapper.map(wallet, WalletDto.class))
                .toList();
    }

    @Override
    public Page<WalletDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
