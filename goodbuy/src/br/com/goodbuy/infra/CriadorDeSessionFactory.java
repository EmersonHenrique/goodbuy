package br.com.goodbuy.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped //o componente será criado uma única vez na aplicação.
public class CriadorDeSessionFactory implements ComponentFactory<SessionFactory>{
	
    private SessionFactory factory;
    
    @PostConstruct //serão executados assim que o escopo for iniciado
    public void abre(){
    	Configuration configuration = new Configuration();
	    configuration.configure();		    
	   	     	     
	  this.factory = configuration.buildSessionFactory();
    }
   
	public SessionFactory getInstance(){
		return this.factory;
	}
	@PreDestroy //erão executados assim que o escopo for finalizado
	public void fecha(){
		this.factory.close();
	}
	}


