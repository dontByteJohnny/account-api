package com.agileengine.challenge.respository;

import com.agileengine.challenge.model.entity.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Account findFirstByOrderByIdDesc();
}
