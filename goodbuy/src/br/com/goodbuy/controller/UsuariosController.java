package br.com.goodbuy.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.goodbuy.dao.UsuarioDao;
import br.com.goodbuy.modelo.Usuario;
import br.com.goodbuy.modelo.UsuarioWeb;

@Resource
public class UsuariosController {
	
	private final UsuarioWeb usuarioWeb;
	private final UsuarioDao dao;
	private Result result;
	private Validator validator;
	
	public UsuariosController(UsuarioDao dao, Result result, Validator validator, UsuarioWeb usuarioWeb) {
		super();
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.usuarioWeb = usuarioWeb;
	}
	
	@Post("/usuarios")
    public void adiciona(Usuario usuario){
    	if(dao.existeUsuario(usuario)){
    	  validator.add(new ValidationMessage("Login já existe", "usuario.login"));	
    	}
    	validator.onErrorUsePageOf(UsuariosController.class).novo();
    	
    	dao.adiciona(usuario);
    	
    	result.redirectTo(ProdutosController.class).lista();
    }


	public void novo(){}
	
	@Get("/login")
	public void loginForm(){}
	
	@Post("/login")
	public void login(Usuario usuario){
		Usuario carrega = dao.carrega(usuario);
		   if(carrega==null){
			 validator.add(
					 new ValidationMessage("Login e/ou senha inválidos",
					 "usuario.login"));  
		   }
		   validator.onErrorUsePageOf(UsuariosController.class).loginForm();
		   usuarioWeb.login(carrega);
		   result.redirectTo(ProdutosController.class).lista();
	}
	
	
	@Path("/logout")
	public void logout(){
		usuarioWeb.logout();
		result.redirectTo(ProdutosController.class).lista();
	}
}
