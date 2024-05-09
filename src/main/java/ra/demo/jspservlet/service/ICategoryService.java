package ra.demo.jspservlet.service;

import ra.demo.jspservlet.dto.request.CategoryRequest;
import ra.demo.jspservlet.model.Category;

public interface ICategoryService extends IGenericService<Category,Integer, CategoryRequest> {
}
