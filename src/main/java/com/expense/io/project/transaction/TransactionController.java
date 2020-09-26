package com.expense.io.project.transaction;

import com.expense.io.base.controller.auth.BaseAuthControllerImpl;
import com.expense.io.project.auth.UserRepository;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("transaction")
@RestController
public class TransactionController
        extends BaseAuthControllerImpl<Transaction, TransactionDTO, TransactionService, TransactionSpecification> {

    public TransactionController(UserRepository repository,
                                 TransactionService service) {
        super(repository, service);
    }


    @GetMapping
    @Override
    public PaginatedResponse<Transaction> list(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy,
                                               TransactionSpecification specification,
                                               HttpServletRequest request) {
        return super.list(page, size, sortBy, specification, request);
    }
}
