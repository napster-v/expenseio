package com.expense.io.project.transaction;

import com.expense.io.base.controller.BaseAuthController;
import com.expense.io.config.Settings;
import com.expense.io.project.auth.AppUser;
import com.expense.io.project.auth.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("transaction")
@RestController
public class TransactionController extends BaseAuthController<Transaction, TransactionDTO, TransactionService> {

    private final TransactionService service;
    private final UserRepository repository;

    public TransactionController(UserRepository repository,
                                 TransactionService service) {
        super(repository, service);
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<TransactionDTO> list(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     Principal principal, TransactionSpecification specification) {
        final Object s = new Settings().getAuthenticatedUser();
        if (startDate == null) startDate = LocalDate.now()
                                                    .withDayOfMonth(1);

        if (endDate == null) endDate = LocalDate.now()
                                                .withDayOfMonth(startDate.lengthOfMonth());

        final AppUser user = repository.getByUsername(principal.getName());

//        return service.findAll(startDate, endDate, user);
        return service.findAll(specification);
    }
}
