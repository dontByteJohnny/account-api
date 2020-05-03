package com.agileengine.challenge.service;

import com.agileengine.challenge.model.dto.AccountResponseDTO;
import com.agileengine.challenge.exception.ApiException;
import com.agileengine.challenge.model.dto.AccountRequestDTO;

import java.util.List;

public interface AccountService {
    AccountResponseDTO postValue(AccountRequestDTO accountRequestDTO) throws ApiException;

    List<AccountResponseDTO> getList();
}