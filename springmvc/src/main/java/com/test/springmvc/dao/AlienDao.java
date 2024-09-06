package com.test.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.springmvc.models.Alien;

@Component
public class AlienDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
//	@Transactional
	public List<Alien> getAliens(){
		Session session = sessionFactory.getCurrentSession();
		List<Alien> aliens = session.createQuery("from Alien", Alien.class).list();
		
		return aliens;
	}
}
