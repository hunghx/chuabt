package ra.demo.jspservlet.service;

import ra.demo.jspservlet.dto.request.ProductRequest;
import ra.demo.jspservlet.model.Product;

public interface IProductService extends IGenericService<Product,Integer, ProductRequest> {
}
