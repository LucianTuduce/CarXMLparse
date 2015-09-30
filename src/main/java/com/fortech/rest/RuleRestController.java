package com.fortech.rest;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

	// <?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
	// <mappingRuleJAXB>
	// <id>1</id>
	// <vehicleAttribute>working</vehicleAttribute>
	// <sourceValue>2000</sourceValue>
	// <targetValue>2500</targetValue>
	// </mappingRuleJAXB>
	@GET
	@Path("/mappingrule")
	@Produces("application/xml")
	public MappingRuleJAXB getMappingRule() {
		return DefaultInitialization.cretaeDeafultMappingRuleJAXB();
	}

	// <?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
	// <marketRuleJAXB>
	// <countryNumber>BH-20-ATD</countryNumber>
	// <branch>12</branch>
	// <stockCategory>NEW</stockCategory>
	// <active>true</active>
	// <rule>work</rule>
	// </marketRuleJAXB>
	@GET
	@Path("/marketrule")
	@Produces("application/xml")
	public MarketRuleJAXB getMarketRule() {
		return DefaultInitialization.creteaDeafultMarketRuleJAXB();
	}

	// {
	// "id": 3,
	// "targetValue": "4999",
	// "sourceValue": "3000",
	// "vehicleAttribute": "good"
	// }

	@GET
	@Path("/mappingrulejson")
	@Produces("application/json")
	public MappingRule getJSONMappingRule() {
		return DefaultInitialization.createDeafultMappingRuleJSON();
	}

	// {
	// "active": false,
	// "branch": 14,
	// "stockCategory": "USED",
	// "countryNumber": "BH-93-TUD",
	// "rule": "private"
	// }
	@GET
	@Path("/marketrulejson")
	@Produces("application/json")
	public MarketRule getJSONMarketRule() {
		return DefaultInitialization.createDefaultMarketRuleJSON();
	}

	@POST
	@Path("/rulexml")
	@Consumes("application/xml")
	public void addWrapperJAXB(final List<WrapperRuleJAXB> ruleJAXBs)
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
	@Path("/rulesimplexml")
	@Consumes("application/xml")
	@Produces("application/xml")
	public List<MappingRuleJAXB> addSimpleWrapperJAXB(
			final WrapperRuleJAXB ruleJAXBs) throws JAXBException {
		List<MappingRuleJAXB> convertedList = new ArrayList<MappingRuleJAXB>();
		if (ruleJAXBs.getRuleType().equals(RuleType.INTERPRETATION)) {

			System.out.println("Do nothing in INTERPRETER");

		} else if (ruleJAXBs.getRuleType().equals(RuleType.MAPPING)) {

			System.out.println("Do nothing in MAPPING");

		} else if (ruleJAXBs.getRuleType().equals(RuleType.MARKET)) {
			StringReader sr = new StringReader(ruleJAXBs.getJsonORxml());
			JAXBContext jaxbContext = JAXBContext
					.newInstance(MappingRuleJAXB.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			MappingRuleJAXB mappingRuleJAXB = (MappingRuleJAXB) unmarshaller
					.unmarshal(sr);
			System.out.println(mappingRuleJAXB.getSourceValue());
			convertedList.add(mappingRuleJAXB);
		}
		return convertedList;
	}

	@GET
	@Path("/wrapper")
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

}
