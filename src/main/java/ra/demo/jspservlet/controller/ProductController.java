package ra.demo.jspservlet.controller;

import ra.demo.jspservlet.dto.request.ProductRequest;
import ra.demo.jspservlet.service.ICategoryService;
import ra.demo.jspservlet.service.IProductService;
import ra.demo.jspservlet.service.impl.CategoryServiceImpl;
import ra.demo.jspservlet.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends HttpServlet {
    private static final IProductService proService = new ProductServiceImpl();
    private static final ICategoryService catService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "list":
                    request.setAttribute("products",proService.findAll());
                    request.setAttribute("categories",catService.findAll());
                    request.getRequestDispatcher("/product.jsp").forward(request,response);
                    break;
                case "edit":
                    Integer idEdit  = Integer.valueOf(request.getParameter("id"));
                    request.setAttribute("product",proService.findById(idEdit));
                    request.setAttribute("categories",catService.findAll());
                    request.getRequestDispatcher("/edit.jsp").forward(request,response);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "add":
                    ProductRequest pro = ProductRequest.builder()
                            .name(request.getParameter("name"))
                            .price(Double.valueOf(request.getParameter("price")))
                            .quantity(Integer.valueOf(request.getParameter("quantity")))
                            .author(request.getParameter("author"))
                            .categoryId(Integer.valueOf(request.getParameter("categoryId")))
                            .build();
                    proService.save(pro);
                    response.sendRedirect("/product?action=list");
                    break;
                    case "update":
                    ProductRequest proUp = ProductRequest.builder()
                            .name(request.getParameter("name"))
                            .price(Double.valueOf(request.getParameter("price")))
                            .quantity(Integer.valueOf(request.getParameter("quantity")))
                            .author(request.getParameter("author"))
                            .categoryId(Integer.valueOf(request.getParameter("categoryId")))
                            .build();
                    proService.save(proUp,Integer.valueOf(request.getParameter("id")));
                    response.sendRedirect("/product?action=list");
                    break;

            }
        }
    }
}