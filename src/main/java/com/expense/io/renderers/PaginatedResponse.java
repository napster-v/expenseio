package com.expense.io.renderers;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@NoArgsConstructor
public class PaginatedResponse<S extends AppBaseModel> {
    private int page;

    private Number nextPage;

    private Number previousPage;

    private int count;

    private int maxPages;

    private long totalCount;

    private String nextPageLink;

    private String previousPageLink;

    private List<? extends BaseDTO> data;

    public PaginatedResponse(Page<S> pagedData, HttpServletRequest request, List<? extends BaseDTO> data) {
        this.page = pagedData.getPageable()
                             .getPageNumber() + 1;
        this.nextPage = getNextPageable(pagedData);
        this.previousPage = getPreviousPageable(pagedData);
        this.count = pagedData.getNumberOfElements();
        this.maxPages = pagedData.getTotalPages();
        this.totalCount = pagedData.getTotalElements();
        this.nextPageLink = getNextPageLink(pagedData, request);
        this.previousPageLink = getPreviousPageLink(pagedData, request);
        this.data = data;
    }

    public Number getNextPageable(Page<S> pagedData) {
        if (pagedData.hasNext()) {
            return pagedData.nextPageable()
                            .getPageNumber() + 1;
        } else {
            return null;
        }
    }

    public Number getPreviousPageable(Page<S> pagedData) {
        if (pagedData.hasPrevious()) {
            return pagedData.previousPageable()
                            .getPageNumber() + 1;
        } else {
            return null;
        }
    }

    public String getNextPageLink(Page<S> pagedData, HttpServletRequest request) {
        if (pagedData.hasNext()) {
            return String.format("%s?page=%d&size=%d",
                                 request.getRequestURL()
                                        .toString(),
                                 pagedData.nextOrLastPageable()
                                          .getPageNumber() + 1, pagedData.getSize());
        } else {
            return null;
        }
    }

    public String getPreviousPageLink(Page<S> pagedData, HttpServletRequest request) {
        if (pagedData.hasPrevious()) {
            return String.format("%s?page=%d&size=%d",
                                 request.getRequestURL()
                                        .toString(),
                                 pagedData.previousOrFirstPageable()
                                          .getPageNumber() + 1, pagedData.getSize());
        } else {
            return null;
        }
    }
}