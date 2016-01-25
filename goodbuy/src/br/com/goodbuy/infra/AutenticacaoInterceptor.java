package br.com.goodbuy.infra;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.goodbuy.controller.Restrito;
import br.com.goodbuy.controller.UsuariosController;
import br.com.goodbuy.modelo.UsuarioWeb;

@Intercepts
public class AutenticacaoInterceptor implements Interceptor{
	
	private final UsuarioWeb usuario;
	private final Result result;
	
	public AutenticacaoInterceptor(UsuarioWeb usuario, Result result){
		this.usuario = usuario;
		this.result = result;
	}
	public boolean accepts(ResourceMethod method){
		return !this.usuario.isLogado() && method.containsAnnotation(Restrito.class);
		//return false;
	}
   public void intercept(InterceptorStack stack, ResourceMethod method,
		   Object resourceInstance)throws InterceptionException{
	   result.redirectTo(UsuariosController.class).loginForm();
	   
   }
}
