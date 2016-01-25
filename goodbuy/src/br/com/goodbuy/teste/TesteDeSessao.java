package br.com.goodbuy.teste;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

public class TesteDeSessao {
	public static void main(String[] args) {

	     AnnotationConfiguration configuration = new AnnotationConfiguration();
	     configuration.configure();
	     SessionFactory factory = configuration.buildSessionFactory();

	     Session session = factory.openSession();

	   }
}
