package com.fortech.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fortech.defaultobjects.DefaultInitialization;
import com.fortech.rule.MappingRule;
import com.fortech.rule.MarketRule;
import com.fortech.rulejaxb.MappingRuleJAXB;
import com.fortech.rulejaxb.MarketRuleJAXB;

@Path("/rule")
public class RuleRestController {	

	@GET
	@Path("/mapping/xml/{idMapppingRule}")
	@Produces("application/xml")
	public MappingRuleJAXB getXMLMappingRule(
			@PathParam("idMapppingRule") int idMapppingRule) {
		return DefaultInitialization.createDeafultMappingRuleJAXB();
	}

	@GET
	@Path("/market/xml/{idMarketRule}")
	@Produces("application/xml")
	public MarketRuleJAXB getXMLMarketRule(
			@PathParam("idMarketRule") int idMarketRule) {
		return DefaultInitialization.creteaDeafultMarketRuleJAXB();
	}

	@GET
	@Path("/mapping/json/{idMapppingRule}")
	@Produces("application/json")
	public MappingRule getJSONMappingRule(
			@PathParam("idMapppingRule") int idMapppingRule) {
		return DefaultInitialization.createDeafultMappingRuleJSON();
	}

	@GET
	@Path("/market/json/{idMarketRule}")
	@Produces("application/json")
	public MarketRule getJSONMarketRule(
			@PathParam("idMarketRule") int idMarketRule) {
		return DefaultInitialization.createDefaultMarketRuleJSON();
	}
	
}
