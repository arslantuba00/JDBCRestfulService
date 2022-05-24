package com.bilgeadam.endpoints;

import java.util.ArrayList;

import com.bilgeadam.model.Ogrenci;
import com.bilgeadam.repo.OgrenciRepo;

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

@Path(value = "/ogrenci")
public class OgrenciResource
{
	@GET
	@Path(value = "/")
	public String getInfo()
	{
		// localhost:8080/JdbcRestfulService/ogrenci
		return "Bu endpoint ogrenciler ile ilgilidir";
	}

	@GET
	@Path(value = "/getAll")
	@Produces(value = MediaType.APPLICATION_JSON)
	public ArrayList<Ogrenci> getAll()
	{
		// localhost:8080/JdbcRestfulService/ogrenci/getAll
		return OgrenciRepo.getAll();
	}

	@GET
	@Path(value = "/getById/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Ogrenci getById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ogrenci/getById/1
		return OgrenciRepo.selectById(id);
	}

	@DELETE
	@Path(value = "/deleteById/{id}")
	public Response deleteById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ogrenci/getById/1
		OgrenciRepo.deleteById(id);
		return Response.ok("Öğrenci silindi").build();
	}

	@POST
	@Path(value = "/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Ogrenci ogrenci)
	{
		// http://localhost:8080/JdbcRestfulService/ogrenci/save
		OgrenciRepo.save(ogrenci);
		return Response.status(Status.CREATED).build();
	}

}
