package com.bilgeadam.utility;


import com.bilgeadam.repository.entity.ParkArea;
import com.bilgeadam.repository.entity.ParkDetail;
import com.bilgeadam.repository.entity.ParkPlace;
import com.bilgeadam.repository.entity.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtils {



	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {
		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(ParkArea.class);
			configuration.addAnnotatedClass(ParkDetail.class);
			configuration.addAnnotatedClass(ParkPlace.class);
			configuration.addAnnotatedClass(Vehicle.class);

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}
