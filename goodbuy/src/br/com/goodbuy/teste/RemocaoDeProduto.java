package br.com.goodbuy.teste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.goodbuy.modelo.Produto;

public class RemocaoDeProduto {
  public static void main(String[] args) {
	  Configuration configuration = new Configuration();
	   configuration.configure();
	   
	   SessionFactory factory = configuration.buildSessionFactory();
	   Session session = factory.openSession();
	   
	   Produto produto = (Produto) session.load(Produto.class, 1L);
	   
	   Transaction tx = session.beginTransaction();
	   session.delete(produto);
	   tx.commit();
}
   
}
