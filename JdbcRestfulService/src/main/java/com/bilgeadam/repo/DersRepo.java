package com.bilgeadam.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.bilgeadam.Constant;
import com.bilgeadam.model.Ders;

public class DersRepo
{

	public static ArrayList<Ders> getAll()
	{
		ArrayList<Ders> liste = new ArrayList<>();
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			Statement stmt = c.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM \"OBS\".\"DERS\"");
			while (result.next())
			{
				Ders ders = new Ders(result.getLong(1), result.getLong(2), result.getLong(3));
				liste.add(ders);
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

	public static Ders selectById(long id)
	{
		Ders ders = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "SELECT * FROM \"OBS\".\"DERS\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			ders = new Ders(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("KONU_ID"));
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ders;
	}

	public static boolean save(Ders ders)
	{
		boolean isInserted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "INSERT INTO \"OBS\".\"DERS\" (\"OGR_ID\", \"KONU_ID\") VALUES (?, ?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, ders.getOGR_ID());
			stmt.setLong(2, ders.getKONU_ID());
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
			String sql = "DELETE FROM \"OBS\".\"DERS\" WHERE \"ID\" = ?";
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
