package com.expense.io.base.specifications;

import com.expense.io.base.model.AppBaseUserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "user.id", constVal = "#{settings.getAuthenticatedUser().id}", valueInSpEL = true, spec = Equal.class)
public interface UserSpecification<S extends AppBaseUserModel> extends Specification<S> {
}
