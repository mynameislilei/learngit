package myweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myweb.util.DBsqlite;

/**
 * Servlet implementation class Db
 */
public class Db extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Db() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBsqlite dbStore = DBsqlite.getInstance();
    	Connection conn = dbStore.getConnection("myweb.db");
		String type = request.getParameter("type");
        String typename = request.getParameter("typename");
        String sql = typename;
       StringBuffer retsult = new StringBuffer();
       int ret = -999;
        if("0".equals(type)){//0.创建数据库
//        	dbStore = DBsqlite.getInstance(typename);
        }else  if("1".equals(type)){//0.创建表
          ret = dbStore.ececute(typename,conn);
        }else  if("2".equals(type)){//0.执行sql
          ret = dbStore.ececute(typename,conn);
        }else  if("3".equals(type)){//0.查询
             ResultSet rs = dbStore.query(sql, conn);
             try {
				while (rs.next()) {
					String symbol = rs.getString("symbol");
					String name = rs.getString("name");
					String id = rs.getString("id");
					retsult.append(id+","+symbol+","+name).append("\n");
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } 
        dbStore.close(conn);
        retsult.append("ret=").append(ret);
        response.getWriter().append("ret: ").append(retsult.toString());
	}

}
