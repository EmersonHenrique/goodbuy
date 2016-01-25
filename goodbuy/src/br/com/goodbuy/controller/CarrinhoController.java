package br.com.goodbuy.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.goodbuy.dao.ProdutoDao;
import br.com.goodbuy.modelo.Carrinho;
import br.com.goodbuy.modelo.Item;
import br.com.goodbuy.modelo.Produto;

@Resource
public class CarrinhoController {
	
	private final Carrinho carrinho;
	private final ProdutoDao dao;
	private final Result result;
	
	public CarrinhoController(Carrinho carrinho, ProdutoDao dao, Result result){
		this.carrinho = carrinho;
		this.dao = dao;
		this.result = result;
	}	

	@Post("/carrinho")
	public void adiciona(Item item){
		dao.recarrega(item.getProduto());
		carrinho.adiciona(item);
		
		//result.redirectTo(ProdutosController.class).lista();
		result.redirectTo(this).visualiza();
	}
	
	@Get("/carrinho")
	public void visualiza(){}	
	
	@Delete("/carrinho/{indiceItem}")
	public void remove(int indiceItem){
		carrinho.remove(indiceItem);
		result.redirectTo(this).visualiza();
	    }

}
