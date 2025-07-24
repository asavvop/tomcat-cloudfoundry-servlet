package com.microsoft.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dbservlet")
public class DbServlet extends HttpServlet {

    @Resource(lookup = "java:comp/env/demo")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        var out = resp.getOutputStream();

        Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name, description from test_table");
			while(rs.next()){
				out.println("Id = "+rs.getInt("id")+", Name = "+rs.getString("name")+", Description = "+rs.getString("description"));
			}
		} catch (SQLException e) {
		   out.println(e.getMessage());
		}finally{
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				} catch (SQLException e) {
					out.println(e.getMessage());
				}
		}


    }

}