package com.cinemaapp.backend.utils;

import com.cinemaapp.backend.controller.dto.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PageConverter {

    public static <E, D> Page<D> convertToPage(
            org.springframework.data.domain.Page<E> springPage,
            Function<E, D> toDomainModelConverter
    ) {
        Page<D> page = new Page<>();
        page.setPageNumber(springPage.getNumber());
        page.setPageSize(springPage.getSize());
        page.setTotalElements((int) springPage.getTotalElements());
        page.setTotalPages(springPage.getTotalPages());
        List<D> domainModels = new ArrayList<>();
        for (E entity : springPage) {
            domainModels.add(toDomainModelConverter.apply(entity));
        }
        page.setContent(domainModels);
        return page;
    }
}
