package com.JWT.JWT.Authnetication.helper;

import Dto.PagebleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <U, V> PagebleResponse<V> getPageble(Page<U> page, Class<V> type) {
        List<V> collect = page.getContent()
                .stream()
                .map(object -> modelMapper.map(object, type))
                .collect(Collectors.toList());

        PagebleResponse<V> pagebleResponse = new PagebleResponse<>();
        pagebleResponse.setContent(collect);  // Set the content

        // Additional fields like page number, size, and total elements can be set as follows:
        pagebleResponse.setPageNumber(page.getNumber());
        pagebleResponse.setPageSize(page.getSize());
        pagebleResponse.setTotalElements(page.getTotalElements());
        pagebleResponse.setTotalPages(page.getTotalPages());
        pagebleResponse.setLastPage(page.isLast());

        return pagebleResponse;  // Return the constructed response
    }
}
