package com.fortech.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fortech.rule.MappingRule;
import com.fortech.rulejaxb.MappingRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

@Path("/service/mapping")
public class MappingRuleRestService {

	@GET
	@Path("/allxml")
	@Produces("application/xml")
	public List<MappingRuleJAXB> getAllXMLMappingRuleJAXB(){
		return null;
	}
	
	@GET
	@Path("/alljson")
	@Produces("application/json")
	public List<MappingRule> getAllJSONMappingRule(){
		return null;
	}
		
	@GET
	@Path("/onexml/{idWrapperRule}")
	@Produces("application/xml")
	public WrapperRuleJAXB getOneXMLMappingRuleJAXB(@PathParam("idWrapperRule") int idWrapperRule){
		return null;
	}
	
	@GET
	@Path("/onejson/{idWrapperRule}")
	@Produces("application/json")
	public WrapperRuleJAXB getOneJSONMappingRule(@PathParam("idWrapperRule") int idWrapperRule){
		return null;
	}
	
	@DELETE
	@Path("/delete/{idWrapperRule}")
	public Response deleteOneMappingRuleJAXBorMappingRule(@PathParam("idWrapperRule") int idWrapperRule){
		return Response.status(200).entity("The object with the id: "+ idWrapperRule+" has been deleted").build();
	}
	
	@PUT
	@Path("/update/mappingjson")
	@Consumes("application/json")
	public Response updateJSONWrapperRuleForMappingRule(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Updated object data: "+wrapperRuleJAXB.toString()).build();
	}
	
	@PUT
	@Path("/update/mappingxml")
	@Consumes("application/xml")
	public Response updateXMLWrapperRuleForMappingRuleJAXB(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Updated object data: "+wrapperRuleJAXB.toString()).build();
	}
	
	@POST
	@Path("/add/mappingjson")
	@Consumes("application/json")
	public Response addJSONWrapperRuleJAXBInDatabaseForMappingRule(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Added object has the data: "+ wrapperRuleJAXB.toString()).build();
	}
	
	@POST
	@Path("/add/mappingxml")
	@Consumes("application/xml")
	public Response addXMLWrapperRuleJAXBInDatabaseForMappingRuleJAXB(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Added object has the data: "+ wrapperRuleJAXB.toString()).build();
	}
	
}