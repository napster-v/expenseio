package com.expense.io.project.transaction;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@And({
        @Spec(path = "category.id", params = "category", spec = Equal.class),
        @Spec(path = "date", params = "startDate", defaultVal = "#{T(java.time.LocalDate).now().withDayOfMonth(1)}", valueInSpEL = true, spec = GreaterThanOrEqual.class),
        @Spec(path = "date", params = "endDate", spec = LessThanOrEqual.class),
        @Spec(path = "user.id", constVal = "#{settings.getAuthenticatedUser().id}", valueInSpEL = true, spec = Equal.class)
})
public interface TransactionSpecification extends Specification<Transaction>,
                                                  JpaSpecificationExecutor<Transaction> {
}
