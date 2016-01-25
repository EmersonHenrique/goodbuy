package br.com.goodbuy.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.goodbuy.modelo.Usuario;

@Component
public class UsuarioDao {
	
	private final Session session;
	
	

	public UsuarioDao(Session session) {
		super();
		this.session = session;
	}
	
	public boolean existeUsuario(Usuario usuario) {
		Usuario encontado = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("login", usuario.getLogin()))
				.uniqueResult();
		return encontado != null;
	}

	public void adiciona(Usuario usuario) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(usuario);
		tx.commit();
		
	}
	
	public Usuario carrega(Usuario usuario){
		return (Usuario) session.createCriteria(Usuario.class)
		  .add(Restrictions.eq("login", usuario.getLogin()))
		  .add(Restrictions.eq("senha", usuario.getSenha()))
		  .uniqueResult();
	}

	
	

}
