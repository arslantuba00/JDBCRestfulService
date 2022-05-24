package com.bilgeadam.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bilgeadam.Constant;
import com.bilgeadam.model.Ders;
import com.bilgeadam.model.Ders_Ogrenci;
import com.bilgeadam.model.Ogrenci;

public class Ders_OgrenciRepo
{

	public static boolean save(Ders ders, Ogrenci ogrenci)
	{
		boolean isInserted = false;
		Connection c = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			c.setAutoCommit(false);
			String sql = "INSERT INTO \"OBS\".\"DERS\" (\"OGR_ID\", \"KONU_ID\") VALUES (?, ?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, ders.getOGR_ID());
			stmt.setLong(2, ders.getKONU_ID());
			stmt.executeUpdate();
			sql = "INSERT INTO \"OBS\".\"OGRENCI\" (\"NAME\", \"OGR_NUMBER\", \"YEAR\") VALUES (?, ?, ?)";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, ogrenci.getNAME());
			stmt.setLong(2, ogrenci.getOGR_NUMBER());
			stmt.setLong(3, ogrenci.getYEAR());
			stmt.executeUpdate();
			sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\" (\"OGR_ID\", \"DERS_ID\", \"DEVAMSIZLIK\", \"NOT\") VALUES (?, ?, ?, ?)";
			stmt = c.prepareStatement(sql);
			stmt.setLong(1, 2);
			stmt.setLong(2, 2);
			stmt.setLong(3, 4);
			stmt.setLong(4, 123);
			isInserted = stmt.executeUpdate() > 0;
			c.commit();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			try
			{
				c.rollback();
				c.close();
			}
			catch (SQLException e1)
			{
			}
			e.printStackTrace();
		}
		return isInserted;
	}

	public static ArrayList<Ders_Ogrenci> getAll()
	{

		ArrayList<Ders_Ogrenci> liste = new ArrayList<>();
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			Statement stmt = c.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM \"OBS\".\"DERS_OGRENCI\"");
			while (result.next())
			{
				Ders_Ogrenci ders_ogrenci = new Ders_Ogrenci(result.getLong(1), result.getLong(2), result.getLong(3), result.getLong(4), result.getLong(5));
				liste.add(ders_ogrenci);
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

	public static Ders_Ogrenci selectById(long id)
	{
		Ders_Ogrenci ders_Ogrenci = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"ID\" = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			ders_Ogrenci = new Ders_Ogrenci(result.getLong("ID"), result.getLong("OGR_ID"), result.getLong("DERS_ID"), result.getLong("DEVAMSIZLIK"), result.getLong("NOT"));
			result.close();
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ders_Ogrenci;
	}

	public static boolean save(Ders_Ogrenci ders_Ogrenci)
	{
		boolean isInserted = false;
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			String sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\" (\"OGR_ID\", \"DERS_ID\", \"DEVAMSIZLIK\", \"NOT\") VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setLong(1, ders_Ogrenci.getOGR_ID());
			stmt.setLong(2, ders_Ogrenci.getDERS_ID());
			stmt.setLong(3, ders_Ogrenci.getDEVAMSIZLIK());
			stmt.setLong(4, ders_Ogrenci.getNOT());
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
			String sql = "DELETE FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"ID\" = ?";
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
