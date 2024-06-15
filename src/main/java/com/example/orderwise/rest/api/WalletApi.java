package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.WalletDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Wallet", description = "REST API for Wallet information")
@RequestMapping("/v1/wallet")
public interface WalletApi {
    @PostMapping
    WalletDto save(@RequestBody WalletDto wallet);
    @PutMapping
    WalletDto update(@RequestBody WalletDto wallet);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    WalletDto getWalletById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<WalletDto> getAllWallet();
    @GetMapping(value = "/search")
    Page<WalletDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
    @GetMapping(value = "/seller/{username}")
    WalletDto getWalletBySeller(@PathVariable("username") String username);
}
