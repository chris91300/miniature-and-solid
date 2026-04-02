package fr.miniature.domain.models.repositories;

import java.util.ArrayList;

public interface repositoryInterface<T> {

    public void save(T t);
    public T findByID(String ID);
    public ArrayList<T> find();    
    //public ArrayList<T> find(int limit);
    public void deleteByID(String ID);
}
