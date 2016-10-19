package net.inscope.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns = { "/" }, loadOnStartup = 0)
public class HelloWorldServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/DefaultDB")
	private DataSource ds;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		PrintWriter writer = response.getWriter();

		writer.println("Trying to obtain datasource...");
		
		try
		{
			if (ds == null)
			{
				writer.println("Resource injection failed!");
			}
			else
			{
				DatabaseMetaData metaData = ds.getConnection().getMetaData();
				
				writer.println("Found: " + metaData.getDatabaseProductName().toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			writer.println("Error occured" + e);
		}
	}       
}
