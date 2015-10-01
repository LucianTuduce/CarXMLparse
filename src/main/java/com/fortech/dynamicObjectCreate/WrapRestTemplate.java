package com.fortech.dynamicObjectCreate;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBException;

import com.fortech.defaultobjects.DefaultInitialization;
import com.fortech.rule.MappingRule;
import com.fortech.rule.MarketRule;
import com.fortech.rulejaxb.MappingRuleJAXB;
import com.fortech.rulejaxb.MarketRuleJAXB;
import com.fortech.wrapper.WrapperRuleJAXB;

@Path("/wrapper")
public class WrapRestTemplate {

	@GET
	@Path("/xml")
	@Produces("application/xml")
	public List<WrapperRuleJAXB> getXMLWrapper() throws JAXBException {
		List<WrapperRuleJAXB> jaxbs = new ArrayList<WrapperRuleJAXB>();
		MappingRuleJAXB mappingRuleJAXB = DefaultInitialization
				.cretaeDeafultMappingRuleJAXB();
		WrapperRuleJAXB wrapperRuleJAXB = DefaultInitialization
				.marshallMappingRulle(mappingRuleJAXB);
		jaxbs.add(wrapperRuleJAXB);

		MarketRuleJAXB marketRule = DefaultInitialization
				.creteaDeafultMarketRuleJAXB();
		WrapperRuleJAXB marketWrapperKAXB = DefaultInitialization
				.marshallMarketRule(marketRule);
		jaxbs.add(marketWrapperKAXB);
		return jaxbs;
	}
	
	@POST
	@Path("/jsonlist/mappingrule")
	@Consumes("application/json")
	public void displayJSONMappingRuleList(final List<MappingRule> mappingRules) {
		for (MappingRule map : mappingRules) {
			System.out.println(map.toString());
		}
	}

	@POST
	@Path("/jsonlist/marketrule")
	@Consumes("application/json")
	public void displayJSONMarketRuleList(final List<MarketRule> marketRules) {
		for (MarketRule market : marketRules) {
			System.out.println(market.toString());
		}
	}
	
}
