package ra.demo.jspservlet.dao.impl;

import ra.demo.jspservlet.dao.ICategoryDao;
import ra.demo.jspservlet.model.Category;
import ra.demo.jspservlet.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    @Override
    public List<Category> findAll() {
        Connection con = ConnectDB.getConnection();
        List<Category> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("select * from Category");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Category category = Category.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .status(rs.getBoolean("status"))
                        .build();
                list.add(category);
            }
            return list;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }

    @Override
    public Category findById(Integer id) {
        Connection con = ConnectDB.getConnection();
        try {
            CallableStatement call = con.prepareCall("select * from Category where id =?" );
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            if (rs.next()){
                Category category = Category.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .status(rs.getBoolean("status"))
                        .build();
              return category;
            }
            return null;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(Integer id) {

    }
}
