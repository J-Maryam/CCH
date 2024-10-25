package com.cch.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID, RequestDto, ResponseDto> {
    ResponseDto save(RequestDto requestDto);
    Optional<ResponseDto> getById(ID id);
    List<ResponseDto> getAll();
    ResponseDto update(ID id, RequestDto requestDto);
    void delete(ID id);
}