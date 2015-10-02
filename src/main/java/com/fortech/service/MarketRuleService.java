package com.fortech.service;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MarketRuleService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void printCeva(){
		System.out.println("INJECTTTTTTT");
	}
}
