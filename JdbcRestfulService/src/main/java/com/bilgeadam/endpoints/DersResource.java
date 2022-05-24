package com.bilgeadam.endpoints;

import java.util.ArrayList;

import com.bilgeadam.model.Ders;
import com.bilgeadam.repo.DersRepo;

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

@Path(value = "/ders")
public class DersResource
{
	@GET
	@Path(value = "/")
	public String getInfo()
	{
		// localhost:8080/JdbcRestfulService/ders
		return "Bu endpoint ders ile ilgilidir";
	}

	@GET
	@Path(value = "/getAll")
	@Produces(value = MediaType.APPLICATION_JSON)
	public ArrayList<Ders> getAll()
	{
		// localhost:8080/JdbcRestfulService/ders/getAll
		return DersRepo.getAll();
	}

	@GET
	@Path(value = "/getById/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Ders getById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ders/getById/1
		return DersRepo.selectById(id);
	}

	@DELETE
	@Path(value = "/deleteById/{id}")
	public Response deleteById(@PathParam(value = "id") Long id)
	{
		// path variable yada path parameter
		// http://localhost:8080/JdbcRestfulService/ders/getById/1
		DersRepo.deleteById(id);
		return Response.ok("Ders silindi").build();
	}

	@POST
	@Path(value = "/save")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response save(Ders ders)
	{
		// http://localhost:8080/JdbcRestfulService/ders/save
		DersRepo.save(ders);
		return Response.status(Status.CREATED).build();
	}

}
