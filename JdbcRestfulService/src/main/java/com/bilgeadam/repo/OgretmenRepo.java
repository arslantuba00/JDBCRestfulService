package com.bilgeadam.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.bilgeadam.Constant;
import com.bilgeadam.model.Ogretmen;

public class OgretmenRepo
{
	public static ArrayList<Ogretmen> getAll()
	{
		ArrayList<Ogretmen> liste = new ArrayList<>();
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			Statement stmt = c.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM \"OBS\".\"OGRETMEN\"");
			while (result.next())
			{
				Ogretmen ogretmen = new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
				liste.add(ogretmen);
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

	public static Ogretmen selectById(long id)
	{
		Ogretmen ogretmen = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "SELECT * FROM \"OBS\".\"OGRETMEN\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			ogretmen = new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ogretmen;
	}

	public static boolean save(Ogretmen ogretmen)
	{
		boolean isInserted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "INSERT INTO \"OBS\".\"OGRETMEN\"(\"NAME\", \"IS_GICIK\") VALUES (?, CAST(? AS bit))";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, ogretmen.getNAME());
			stmt.setObject(2, ogretmen.isIS_GICIK() ? 1 : 0);
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
			String sql = "DELETE FROM \"OBS\".\"OGRETMEN\" WHERE \"ID\" = ?";
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
