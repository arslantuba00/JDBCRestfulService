package com.bilgeadam.endpoints;

import java.util.ArrayList;

import com.bilgeadam.model.Ogretmen;
import com.bilgeadam.repo.OgretmenRepo;

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

@Path(value = "/ogretmen")
public class OgretmenResource
{
	@GET
	@Path(value = "/")
	public String getInfo()
	{
		// localhost:8080/JdbcRestfulService/ogretmen
		return "Bu endpoint ogretmenler ile ilgilidir";
	}

	@GET
	@Path(value = "/getAll")
	@Produces(value = MediaType.APPLICATION_JSON)
	public ArrayList<Ogretmen> getAll()
	{
		// localhost:8080/JdbcRestfulService/ogretmen/getAll
		return OgretmenRepo.getAll();
	}

	@GET
	@Path(value = "/getById/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Ogretmen getById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ogretmen/getById/1
		return OgretmenRepo.selectById(id);
	}

	@DELETE
	@Path(value = "/deleteById/{id}")
	public Response deleteById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ogretmen/deleteById/1
		OgretmenRepo.deleteById(id);
		return Response.ok("Öğretmen silindi").build();
	}

	@POST
	@Path(value = "/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Ogretmen ogretmen)
	{
		// http://localhost:8080/JdbcRestfulService/ogretmen/save
		OgretmenRepo.save(ogretmen);
		return Response.status(Status.CREATED).build();
	}

}
