package com.agileengine.challenge.controller;

import com.agileengine.challenge.model.dto.AccountResponseDTO;
import com.agileengine.challenge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/balance")
public class FrontController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public String getAccountList(Model model) {
        List<AccountResponseDTO> accountList = accountService.getList();
        model.addAttribute("accounts", accountList);
        return "accountList";
    }
}
