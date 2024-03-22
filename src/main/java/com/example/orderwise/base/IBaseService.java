package com.example.orderwise.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E, D extends Serializable> extends IRsqlService<E, D > {
    D save(D dto) throws Exception;
    D update(D dto);
    void delete(Long id);
    D findById(Long id);
    List<D> findAll();
}
