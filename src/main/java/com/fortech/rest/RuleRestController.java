package com.fortech.rest;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fortech.defaultobjects.DefaultInitialization;
import com.fortech.rule.MappingRule;
import com.fortech.rule.MarketRule;
import com.fortech.rulejaxb.MappingRuleJAXB;
import com.fortech.rulejaxb.MarketRuleJAXB;
import com.fortech.ruletype.RuleType;
import com.fortech.wrapper.WrapperRuleJAXB;

@Path("/rule")
public class RuleRestController {

	private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	@GET
	@Path("/mapping/xml/{idMapppingRule}")
	@Produces("application/xml")
	public MappingRuleJAXB getXMLMappingRule(@PathParam("idMapppingRule") int idMapppingRule) {
		return DefaultInitialization.cretaeDeafultMappingRuleJAXB();
	}

	@GET
	@Path("/market/xml/{idMarketRule}")
	@Produces("application/xml")
	public MarketRuleJAXB getXMLMarketRule(@PathParam("idMarketRule") int idMarketRule) {
		return DefaultInitialization.creteaDeafultMarketRuleJAXB();
	}


	@GET
	@Path("/mapping/json/{idMapppingRule}")
	@Produces("application/json")
	public MappingRule getJSONMappingRule(@PathParam("idMapppingRule") int idMapppingRule) {
		return DefaultInitialization.createDeafultMappingRuleJSON();
	}

	@GET
	@Path("/market/json/{idMarketRule}")
	@Produces("application/json")
	public MarketRule getJSONMarketRule(@PathParam("idMarketRule") int idMarketRule) {
		return DefaultInitialization.createDefaultMarketRuleJSON();
	}

	@POST
	@Path("/wrapper/xml")
	@Consumes("application/xml")
	public void createMarketRuleOrMappingRuleOrInterpretationRuleFromXMLWrapper(final List<WrapperRuleJAXB> ruleJAXBs)
			throws JAXBException {
		for (WrapperRuleJAXB jaxb : ruleJAXBs) {
			if (jaxb.getRuleType().equals(RuleType.INTERPRETATION)) {

				System.out.println("Do nothing in INTERPRETER");

			} else if (jaxb.getRuleType().equals(RuleType.MAPPING)) {

				StringReader sr = new StringReader(XML_VERSION
						+ jaxb.getJsonORxml());
				JAXBContext jaxbContext = JAXBContext
						.newInstance(MappingRuleJAXB.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				MappingRuleJAXB mappingRuleJAXB = (MappingRuleJAXB) unmarshaller
						.unmarshal(sr);
				System.out.println(mappingRuleJAXB.toString());

			} else if (jaxb.getRuleType().equals(RuleType.MARKET)) {

				StringReader sr = new StringReader(XML_VERSION
						+ jaxb.getJsonORxml());
				JAXBContext jaxbContext = JAXBContext
						.newInstance(MarketRuleJAXB.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				MarketRuleJAXB marketRuleJAXB = (MarketRuleJAXB) unmarshaller
						.unmarshal(sr);
				System.out.println(marketRuleJAXB.toString());
			}
		}

	}

	@POST
	@Path("/wrapper/json")
	@Consumes("application/json")
	public void createMarketRuleOrMappingRuleOrInterpretationRuleFromJSONWrapper(
			final List<WrapperRuleJAXB> wrapperRuleJAXBs) throws JsonParseException, JsonMappingException, IOException {
		for (WrapperRuleJAXB wrap : wrapperRuleJAXBs) {
			if (wrap.getRuleType().equals(RuleType.INTERPRETATION)) {

				System.out.println("Do nothing for interpretation");

			} else if (wrap.getRuleType().equals(RuleType.MAPPING)) {

				MappingRule mappingRule = new ObjectMapper().readValue(wrap.getJsonORxml(), MappingRule.class);
				System.out.println(mappingRule.toString());

			} else if (wrap.getRuleType().equals(RuleType.MARKET)) {

				MarketRule marketRule = new ObjectMapper().readValue(wrap.getJsonORxml(), MarketRule.class); 
				System.out.println(marketRule.toString());

			}
		}
	}

}
