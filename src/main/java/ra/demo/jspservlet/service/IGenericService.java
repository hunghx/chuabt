package ra.demo.jspservlet.service;

import java.util.List;

public interface IGenericService <T,E,R>{
    List<T> findAll();
    T findById(E id);
    void save(R t);
    void save(R t,E id);
    void delete(Integer id);
}
