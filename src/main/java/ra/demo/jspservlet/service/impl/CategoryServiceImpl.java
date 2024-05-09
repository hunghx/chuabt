package ra.demo.jspservlet.service.impl;

import ra.demo.jspservlet.dao.ICategoryDao;
import ra.demo.jspservlet.dao.IProductDao;
import ra.demo.jspservlet.dao.impl.CategoryDaoImpl;
import ra.demo.jspservlet.dao.impl.ProductDaoImpl;
import ra.demo.jspservlet.dto.request.CategoryRequest;
import ra.demo.jspservlet.model.Category;
import ra.demo.jspservlet.service.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private static final ICategoryDao daoCat =new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return daoCat.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public void save(CategoryRequest t) {

    }

    @Override
    public void save(CategoryRequest t, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
