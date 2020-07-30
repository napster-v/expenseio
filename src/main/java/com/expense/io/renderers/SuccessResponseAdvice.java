package com.expense.io.renderers;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class SuccessResponseAdvice<T> implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse
                                 ) {
        if (methodParameter.getContainingClass()
                           .isAnnotationPresent(RestController.class) && !(o instanceof ErrorResponse) && !(o instanceof PaginatedResponse)) {
            return new SuccessResponse<>(o);
        }
        return o;
    }
}
