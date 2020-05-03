package com.agileengine.challenge.service.impl;

import com.agileengine.challenge.respository.AccountRepository;
import com.agileengine.challenge.exception.ApiException;
import com.agileengine.challenge.model.dto.AccountRequestDTO;
import com.agileengine.challenge.model.dto.AccountResponseDTO;
import com.agileengine.challenge.model.entity.Account;
import com.agileengine.challenge.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public AccountResponseDTO postValue(AccountRequestDTO accountRequestDTO) throws ApiException {
        BigDecimal lastAccountValue = accountRepository.findFirstByOrderByIdDesc().getAmount();
        BigDecimal updatedValue = lastAccountValue != null ?
                (accountRequestDTO.getType().toLowerCase().equals("credit") ?
                        lastAccountValue.add(accountRequestDTO.getAmount()) :
                        lastAccountValue.subtract(accountRequestDTO.getAmount()))
                : accountRequestDTO.getAmount();
        if (updatedValue.compareTo(BigDecimal.ZERO) == -1) {
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE.value(), "Insufficient funds!");
        }
        Account newAccountMovement = Account.builder()
                .amount(updatedValue)
                .effectiveDate(Calendar.getInstance())
                .type(accountRequestDTO.getType().toLowerCase())
                .build();
        accountRepository.save(newAccountMovement);
        return mapper.map(newAccountMovement, AccountResponseDTO.class);
    }

    @Override
    public List<AccountResponseDTO> getList() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return accounts.stream().map(a -> mapper.map(a, AccountResponseDTO.class)).collect(Collectors.toList());
    }
}