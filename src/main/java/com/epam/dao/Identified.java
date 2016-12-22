package com.epam.dao;

import java.io.Serializable;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    public PK getId();
}