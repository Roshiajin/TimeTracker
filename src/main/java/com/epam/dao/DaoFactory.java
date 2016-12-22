package com.epam.dao;

/**
 * Created by Alexander_Gaptullin on 12/20/2016.
 */
public interface DaoFactory<Context> {

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    public Context getContext() throws PersistException;

    public GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
