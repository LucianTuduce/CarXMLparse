package com.fortech.wrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fortech.ruletype.RuleType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperRuleJAXB {

	@XmlElement(required = true)
	private RuleType ruleType;
	@XmlElement(required = true)
	private String jsonORxml;
	
	public RuleType getRuleType() {
		return ruleType;
	}
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	public String getJsonORxml() {
		return jsonORxml;
	}
	public void setJsonORxml(String jsonORxml) {
		this.jsonORxml = jsonORxml;
	}
	
	public String toString(){
		return "[ "+ruleType+" "+jsonORxml+" ]";
	}
}
