package com.bilgeadam.endpoints;

import java.util.ArrayList;

import com.bilgeadam.model.Ders_Ogrenci;
import com.bilgeadam.repo.Ders_OgrenciRepo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path(value = "/ders_ogrenci")
public class Ders_OgrenciResource
{
	@GET
	@Path(value = "/")
	public String getInfo()
	{
		// localhost:8080/JdbcRestfulService/ders_ogrenci
		return "Bu endpoint ders_ogrenci ile ilgilidir";
	}

	@GET
	@Path(value = "/getAll")
	@Produces(value = MediaType.APPLICATION_JSON)
	public ArrayList<Ders_Ogrenci> getAll()
	{
		// localhost:8080/JdbcRestfulService/ders_ogrenci/getAll
		return Ders_OgrenciRepo.getAll();
	}

	@GET
	@Path(value = "/getById/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Ders_Ogrenci getById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ders_ogrenci/getById/1
		return Ders_OgrenciRepo.selectById(id);
	}

	@DELETE
	@Path(value = "/deleteById/{id}")
	public Response deleteById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ders_ogrenci/getById/1
		Ders_OgrenciRepo.deleteById(id);
		return Response.ok("Ders silindi").build();
	}

	@POST
	@Path(value = "/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Ders_Ogrenci ders_ogrenci)
	{
		// http://localhost:8080/JdbcRestfulService/ders_ogrenci/save
		Ders_OgrenciRepo.save(ders_ogrenci);
		return Response.status(Status.CREATED).build();
	}

}
