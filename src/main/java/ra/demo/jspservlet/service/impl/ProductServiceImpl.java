package ra.demo.jspservlet.service.impl;

import ra.demo.jspservlet.dao.ICategoryDao;
import ra.demo.jspservlet.dao.IProductDao;
import ra.demo.jspservlet.dao.impl.CategoryDaoImpl;
import ra.demo.jspservlet.dao.impl.ProductDaoImpl;
import ra.demo.jspservlet.dto.request.ProductRequest;
import ra.demo.jspservlet.model.Category;
import ra.demo.jspservlet.model.Product;
import ra.demo.jspservlet.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    private static final IProductDao daoPro =new ProductDaoImpl();
    private static final ICategoryDao daoCat =new CategoryDaoImpl();

    @Override
    public List<Product> findAll() {
        return daoPro.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return daoPro.findById(id);
    }

    @Override
    public void save(ProductRequest t) {
        Category cat = daoCat.findById(t.getCategoryId());
        if (cat ==null){
            throw new RuntimeException("id not found");
        }
        Product newPro  = Product.builder()
                .name(t.getName())
                .price(t.getPrice())
                .author(t.getAuthor())
                .quantity(t.getQuantity())
                .category(cat)
                .build();
        daoPro.save(newPro);
    }

    @Override
    public void save(ProductRequest t, Integer id) {
        Category cat = daoCat.findById(t.getCategoryId());
        if (cat ==null){
            throw new RuntimeException("id not found");
        }
        Product newPro  = Product.builder()
                .id(id)
                .name(t.getName())
                .price(t.getPrice())
                .author(t.getAuthor())
                .quantity(t.getQuantity())
                .category(cat)
                .build();
        daoPro.save(newPro);
    }

    @Override
    public void delete(Integer id) {
        daoPro.delete(id);
    }
}
