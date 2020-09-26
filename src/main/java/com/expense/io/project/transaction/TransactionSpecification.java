package com.expense.io.project.transaction;

import com.expense.io.base.specifications.UserSpecification;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
        @Spec(path = "category.id", params = "category", spec = Equal.class),
        @Spec(path = "date", params = "startDate", defaultVal = "#{T(java.time.LocalDate).now().withDayOfMonth(1)}", valueInSpEL = true, spec = GreaterThanOrEqual.class),
        @Spec(path = "date", params = "endDate", spec = LessThanOrEqual.class),
})
public interface TransactionSpecification extends UserSpecification<Transaction> {
}
