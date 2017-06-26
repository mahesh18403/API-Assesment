package com.assignment.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.dao.MetaDataFieldsRepository;
import com.assignment.model.MetaDataFields;

@Path("/")
@Component
public class RequestController {
	
	@Autowired
	private MetaDataFieldsRepository repository;
	
	@GET
	public Response getMessage() {
		return Response.status(200).entity("success").build();
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream file) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(file));
		String line = null;

		while((line = in.readLine()) != null) {
		    String[] split = line.split(":");
		    
		    MetaDataFields fields = new MetaDataFields();
		    fields.setName(split[0]);
		    fields.setValue(split[1]);
		    fields.setCreatedDate(new Date());
		    
		    repository.save(fields);
		}
		
		return Response.status(200).entity("successfully stored").build();
	}
	
	@GET
	@Path("/getById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MetaDataFields getMetaData(@PathParam("id") Long id) {
		MetaDataFields field = repository.findOne(id);
		return field;
	}
	
	@GET
	@Path("/getAllRecords")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MetaDataFields> getAllRecords() {
		List<MetaDataFields> field = repository.findAll();
		return field;
	}
	
}
