package com.fortech.wrapper;

import com.fortech.ruletype.RuleType;

public class WrapperRule {

	private RuleType ruleType;
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
}
