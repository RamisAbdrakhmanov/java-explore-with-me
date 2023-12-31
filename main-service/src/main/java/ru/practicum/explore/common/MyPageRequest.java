package ru.practicum.explore.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MyPageRequest extends PageRequest {

    protected MyPageRequest(int page, int size, Sort sort) {
        super(page / size, size, sort);
    }

    public static MyPageRequest of(int page, int size) {
        return new MyPageRequest(page, size, Sort.unsorted());
    }

    public static MyPageRequest of(int page, int size, String sort) {
        return new MyPageRequest(page, size, Sort.by(sort));
    }

}
