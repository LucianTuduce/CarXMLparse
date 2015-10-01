package com.fortech.rest;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;

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
	public static final int PRETTY_PRINT_INDENT_FACTOR = 4;

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

	@POST
	@Path("/wrapper/xml")
	@Consumes("application/xml")
	public void createMarketRuleOrMappingRuleOrInterpretationRuleFromXMLWrapper(
			final List<WrapperRuleJAXB> ruleJAXBs) throws JAXBException {
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
			final List<WrapperRuleJAXB> wrapperRuleJAXBs)
			throws JsonParseException, JsonMappingException, IOException {
		for (WrapperRuleJAXB wrap : wrapperRuleJAXBs) {
			if (wrap.getRuleType().equals(RuleType.INTERPRETATION)) {

				System.out.println("Do nothing for interpretation");

			} else if (wrap.getRuleType().equals(RuleType.MAPPING)) {

				MappingRule mappingRule = new ObjectMapper().readValue(
						wrap.getJsonORxml(), MappingRule.class);
				System.out.println(mappingRule.toString());

			} else if (wrap.getRuleType().equals(RuleType.MARKET)) {

				MarketRule marketRule = new ObjectMapper().readValue(
						wrap.getJsonORxml(), MarketRule.class);
				System.out.println(marketRule.toString());

			}
		}
	}

	@GET
	@Path("/wrapper/data")
	@Produces("application/json")
	public List<WrapperRuleJAXB> getWrapperdDataForInserting()
			throws PropertyException, JAXBException {
		List<WrapperRuleJAXB> wrapperList = new ArrayList<WrapperRuleJAXB>();

		String map = convertXMLtoJSON(DefaultInitialization
				.createDefaultMarshallMappingRuleJAXB());
		WrapperRuleJAXB wrapperRuleJAXB = new WrapperRuleJAXB();
		wrapperRuleJAXB.setRuleType(RuleType.MAPPING);
		wrapperRuleJAXB.setJsonORxml(map);
		wrapperList.add(wrapperRuleJAXB);
		String market = convertXMLtoJSON(DefaultInitialization
				.createDefaultMarshallMarketRuleJAXB());
		WrapperRuleJAXB wrapperRuleJAXB2 = new WrapperRuleJAXB();
		wrapperRuleJAXB2.setRuleType(RuleType.MARKET);
		wrapperRuleJAXB2.setJsonORxml(market);
		wrapperList.add(wrapperRuleJAXB2);
		return wrapperList;
	}

	private static String convertXMLtoJSON(String xml) {
		JSONObject jsonpObject = XML.toJSONObject(xml);
		String prettyJson = jsonpObject.toString();
		String json = createSimpleJSON(prettyJson);
		return json;
	}

	private static String createSimpleJSON(String json) {
		String a = "";
		boolean canIdoIt = false;
		for (int i = 1; i < json.length(); i++) {
			char c = json.charAt(i);
			if (c == '{' || canIdoIt) {
				a = a + c;
				canIdoIt = true;
			}
			if (c == '}') {
				break;
			}
		}

		return a;
	}

}
