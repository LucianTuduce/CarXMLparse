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

import com.fortech.rule.MarketRule;
import com.fortech.rulejaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

@Path("/service/market")
public class MarketRuleRestService {

	@GET
	@Path("/allxml")
	@Produces("application/xml")
	public List<MarketRuleJAXB> getAllXMLMarketRuleJAXB(){
		return null;
	}
	
	@GET
	@Path("/alljson")
	@Produces("application/json")
	public List<MarketRule> getAllJSONMarketRule(){
		return null;
	}
		
	@GET
	@Path("/onexml/{idWrapperRule}")
	@Produces("application/xml")
	public WrapperRuleJAXB getOneXMLMarketRuleJAXB(@PathParam("idWrapperRule") int idWrapperRule){
		return null;
	}
	
	@GET
	@Path("/onejson/{idWrapperRule}")
	@Produces("application/json")
	public WrapperRuleJAXB getOneJSONMarketRule(@PathParam("idWrapperRule") int idWrapperRule){
		return null;
	}
	
	@DELETE
	@Path("/delete/{idWrapperRule}")
	public Response deleteOneMarketRuleJAXBorMarketRule(@PathParam("idWrapperRule") int idWrapperRule){
		return Response.status(200).entity("The object with the id: "+ idWrapperRule+" has been deleted").build();
	}
	
	@PUT
	@Path("/update/marketjson")
	@Consumes("application/json")
	public Response updateJSONWrapperRuleForMarketRule(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Updated object data: "+wrapperRuleJAXB.toString()).build();
	}
	
	@PUT
	@Path("/update/marketxml")
	@Consumes("application/xml")
	public Response updateXMLWrapperRuleForMarketRuleJAXB(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Updated object data: "+wrapperRuleJAXB.toString()).build();
	}
	
	@POST
	@Path("/add/marketjson")
	@Consumes("application/json")
	public Response addJSONWrapperRuleJAXBInDatabaseForMarketRule(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Added object has the data: "+ wrapperRuleJAXB.toString()).build();
	}
	
	@POST
	@Path("/add/marketxml")
	@Consumes("application/xml")
	public Response addXMLWrapperRuleJAXBInDatabaseForMarketRuleJAXB(final WrapperRuleJAXB wrapperRuleJAXB){
		return Response.status(200).entity("Added object has the data: "+ wrapperRuleJAXB.toString()).build();
	}
	
}
