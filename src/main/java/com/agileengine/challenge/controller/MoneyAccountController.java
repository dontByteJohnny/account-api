package com.agileengine.challenge.controller;

import com.agileengine.challenge.exception.ApiException;
import com.agileengine.challenge.model.dto.AccountRequestDTO;
import com.agileengine.challenge.model.dto.AccountResponseDTO;
import com.agileengine.challenge.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Api(tags = "Money Account Manage API")
public class MoneyAccountController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Echo health")
    @GetMapping("/echo")
    public String checkAvailability(@RequestParam(name = "message") String message) {
        return "ECHO IS WORKING!:" + message;
    }

    @ApiOperation(value = "Post monetary value of user")
    @PostMapping()
    public AccountResponseDTO postValue(@Valid @RequestBody AccountRequestDTO accountRequestDTO) throws ApiException {
        return accountService.postValue(accountRequestDTO);
    }

}
