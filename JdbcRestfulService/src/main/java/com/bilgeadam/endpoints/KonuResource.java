package com.bilgeadam.endpoints;

import java.util.ArrayList;

import com.bilgeadam.model.Konu;
import com.bilgeadam.repo.KonuRepo;

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

@Path(value = "/konu")
public class KonuResource
{
	@GET
	@Path(value = "/")
	public String getInfo()
	{
		// localhost:8080/JdbcRestfulService/konu
		return "Bu endpoint konu ile ilgilidir";
	}

	@GET
	@Path(value = "/getAll")
	@Produces(value = MediaType.APPLICATION_JSON)
	public ArrayList<Konu> getAll()
	{
		// localhost:8080/JdbcRestfulService/konu/getAll
		return KonuRepo.getAll();
	}

	@GET
	@Path(value = "/getById/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Konu getById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/konu/getById/1
		return KonuRepo.selectById(id);
	}

	@DELETE
	@Path(value = "/deleteById/{id}")
	public Response deleteById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/konu/getById/1
		KonuRepo.deleteById(id);
		return Response.ok("Konu silindi").build();
	}

	@POST
	@Path(value = "/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Konu konu)
	{
		// http://localhost:8080/JdbcRestfulService/konu/save
		KonuRepo.save(konu);
		return Response.status(Status.CREATED).build();
	}

}
