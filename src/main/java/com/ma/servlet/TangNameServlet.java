package com.ma.servlet;

import com.ma.dao.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/tangname")
public class TangNameServlet extends HttpServlet {
    private Connection connection = null;
    private PreparedStatement statement = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        try {
            connection = DBUtil.getConnection();
            String sql = "select title from tangshi order by id";
             statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<String> titles = new ArrayList<>();
            while (rs.next()){
                String title = rs.getString("title");
                titles.add(title);
            }
            PrintWriter writer = resp.getWriter();
            writer.println("<h1>唐诗三百首</h1>");
            writer.println("<ol>");

            for (String title : titles) {
                writer.println("<li>" + title + "</li>");
            }
            writer.println("</ol>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
