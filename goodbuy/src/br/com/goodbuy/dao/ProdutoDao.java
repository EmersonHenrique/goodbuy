package br.com.goodbuy.dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.goodbuy.infra.CriadorDeSession;
import br.com.goodbuy.modelo.Produto;

@Component
public class ProdutoDao {

	private final Session session;
	
	public ProdutoDao(Session session){
		this.session = session;
	}
	
	public void salva(Produto produto) {
		Transaction tx = session.beginTransaction();
	     session.save(produto);        
	     tx.commit();
	}

	public void altera(Produto produto) {
		Transaction tx = session.beginTransaction();
		produto.setPreco(42.5);
		session.update(produto);
		tx.commit();
	}
	
	public List<Produto> listaTudo(){
		return this.session.createCriteria(Produto.class).list();
	}

	public Produto carrega(Long id) {
		return (Produto) this.session.load(Produto.class, id);
		//return (Produto) this.session.load(Produto.class, id);
	}

	public void atualiza(Produto produto) {
		Transaction tx = session.beginTransaction();
		this.session.update(produto);
		tx.commit();
	}

	public void remove(Produto produto) {
		Transaction tx = session.beginTransaction();
		this.session.delete(produto); 
		tx.commit();		
	}

	public List<Produto> busca(String nome) {		
		return session.createCriteria(Produto.class).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE)).list();
	}

	public void recarrega(Produto produto) {
		session.refresh(produto);
		
	}	
}
