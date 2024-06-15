package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.WalletDto;
import com.example.orderwise.rest.api.WalletApi;
import com.example.orderwise.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WalletController implements WalletApi {
    private final WalletService walletService;

    @Override
    public WalletDto save(WalletDto wallet) {
        return walletService.save(wallet);
    }

    @Override
    public WalletDto update(WalletDto wallet) {
        return walletService.update(wallet);
    }

    @Override
    public void delete(Long id) {
        walletService.delete(id);
    }

    @Override
    public WalletDto getWalletById(Long id) {
        return walletService.findById(id);
    }

    @Override
    public List<WalletDto> getAllWallet() {
        return walletService.findAll();
    }

    @Override
    public Page<WalletDto> search(String query, Integer page, Integer size, String order, String sort) {
        return walletService.rsqlQuery(query, page, size, order, sort);
    }

    public WalletDto getWalletBySeller(String username) {
        return walletService.getWalletBySeller(username);
    }
}
