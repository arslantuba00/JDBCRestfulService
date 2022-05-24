package com.bilgeadam.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.bilgeadam.Constant;
import com.bilgeadam.model.Ogrenci;

public class OgrenciRepo
{

	public static ArrayList<Ogrenci> getAll()
	{
		ArrayList<Ogrenci> liste = new ArrayList<>();
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			Statement stmt = c.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM \"OBS\".\"OGRENCI\"");
			while (result.next())
			{
				Ogrenci ogrenci = new Ogrenci(result.getLong(1), result.getString("NAME"), result.getLong(3), result.getLong(4));
				liste.add(ogrenci);
			}
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return liste;
	}

	public static Ogrenci selectById(long id)
	{
		Ogrenci ogrenci = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "SELECT * FROM \"OBS\".\"OGRENCI\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			ogrenci = new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("NUMBER"), result.getLong("YEAR"));
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ogrenci;
	}

	public static boolean save(Ogrenci ogrenci)
	{
		boolean isInserted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "INSERT INTO \"OBS\".\"OGRENCI\" (\"NAME\", \"NUMBER\", \"YEAR\") VALUES (?, ?, ?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, ogrenci.getNAME());
			stmt.setLong(2, ogrenci.getOGR_NUMBER());
			stmt.setLong(3, ogrenci.getYEAR());
			isInserted = stmt.executeUpdate() > 0;
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isInserted;
	}

	public static boolean deleteById(long id)
	{
		boolean isDeleted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "DELETE FROM \"OBS\".\"OGRENCI\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			isDeleted = stmt.executeUpdate() > 0;
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isDeleted;
	}
}
