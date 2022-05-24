package com.bilgeadam.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.bilgeadam.Constant;
import com.bilgeadam.model.Konu;

public class KonuRepo
{

	public static ArrayList<Konu> getAll()
	{
		ArrayList<Konu> liste = new ArrayList<>();
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			Statement stmt = c.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM \"OBS\".\"KONU\"");
			while (result.next())
			{
				Konu konu = new Konu(result.getLong(1), result.getString(2));
				liste.add(konu);
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

	public static Konu selectById(long id)
	{
		Konu konu = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "SELECT * FROM \"OBS\".\"KONU\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			konu = new Konu(result.getLong("ID"), result.getString("NAME"));
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return konu;
	}

	public static boolean save(Konu konu)
	{
		boolean isInserted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "INSERT INTO \"OBS\".\"KONU\" (\"NAME\") VALUES (?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, konu.getNAME());
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
			String sql = "DELETE FROM \"OBS\".\"KONU\" WHERE \"ID\" = ?";
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
