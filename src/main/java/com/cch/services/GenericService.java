package com.cch.services;

import java.util.List;

public interface GenericService<T, ID, RequestDto, ResponseDto> {
    ResponseDto create(RequestDto requestDto);
    ResponseDto getById(ID id);
    List<ResponseDto> getAll();
    ResponseDto update(ID id, RequestDto requestDto);
    void delete(ID id);
}
