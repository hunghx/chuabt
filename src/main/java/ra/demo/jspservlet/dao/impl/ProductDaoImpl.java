package ra.demo.jspservlet.dao.impl;

import ra.demo.jspservlet.dao.IProductDao;
import ra.demo.jspservlet.model.Category;
import ra.demo.jspservlet.model.Product;
import ra.demo.jspservlet.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Product> findAll() {
        Connection con = ConnectDB.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            CallableStatement cstmt = con.prepareCall("select p.*,c.name categoryName, c.status from product p join Category c on p.category_id = c.id");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Category  cat = Category.builder()
                        .id(rs.getInt("category_id"))
                        .name(rs.getString("categoryName"))
                        .status(rs.getBoolean("status"))
                        .build();
                Product product = Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .author(rs.getString("author"))
                        .quantity(rs.getInt("stock"))
                        .category(cat)
                        .build();
                list.add(product);
            }
            return list;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }

    @Override
    public Product findById(Integer id) {
        Connection con = ConnectDB.getConnection();
        try {
            CallableStatement cstmt = con.prepareCall("select p.*,c.name as categoryName, c.status from product p join Category c on p.category_id = c.id where p.id = ?");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                Category  cat = Category.builder()
                        .id(rs.getInt("category_id"))
                        .name(rs.getString("categoryName"))
                        .status(rs.getBoolean("status"))
                        .build();
                return Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .author(rs.getString("author"))
                        .quantity(rs.getInt("stock"))
                        .category(cat)
                        .build();
            }
            return null;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }

    @Override
    public void save(Product product) {
        Connection con = ConnectDB.getConnection();
        CallableStatement call = null;
        try {
            if (product.getId() == null) {
                call = con.prepareCall("insert into product(name, price, stock, author, category_id) value (?,?,?,?,?)");
            } else {
                call = con.prepareCall("update product set name= ?, price= " +
                        "?, stock=?, author=?, category_id=? where id = ?");
                call.setInt(6,product.getId());
            }
            call.setString(1, product.getName());
            call.setDouble(2,product.getPrice());
            call.setInt(3,product.getQuantity());
            call.setString(4,product.getAuthor());
            call.setInt(5,product.getCategory().getId());
            call.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection con = ConnectDB.getConnection();
        try {
            CallableStatement cstmt = con.prepareCall("delete  from  product where id = ?");
            cstmt.setInt(1, id);
            cstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }
}
