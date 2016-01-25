 package br.com.goodbuy.controller;

import java.util.*;

import static br.com.caelum.vraptor.view.Results.*;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.goodbuy.dao.ProdutoDao;
import br.com.goodbuy.modelo.Produto;

@Resource
public class ProdutosController {
	
	private final ProdutoDao dao;
	private final Result result;
	private final Validator validator;
	
	ProdutosController(ProdutoDao dao, Result result, Validator validator){
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}
	
	@Get("/produtos")
	public List<Produto> lista(){
      return dao.listaTudo();
	}

	@Restrito
    @Post("/produtos")	
    public void adiciona(final Produto produto){
	/*
  if(produto.getNome() == null || 
	 produto.getNome().length() < 3){
   validator.add(new ValidationMessage(
		   "Nome é obrigatório e precisa ter mais" +
            " de 3 letras" , "produto.nome")); 
    }
  if(produto.getDescricao()==null){
		  //||
    // produto.getDescricao().length() > 0){
  validator.add(new ValidationMessage(
			   "Descrição é obrigatório e precisa ter mais" +
	            " de 4 letras" , "produto.decricao"));  
  }
  
  if(produto.getPreco()==null ||
		     produto.getPreco() <= 0){
		  validator.add(new ValidationMessage(
					   " Preço precisa ser positivo " , "produto.preco"));  
		  } 
//	validator.validate(produto); //cap 10
	
	validator.onErrorUsePageOf(ProdutosController.class).formulario();
	*/
		dao.salva(produto);
		result.redirectTo(this).lista();
	}
	
    @Restrito
    @Get("/produtos/{id}")
	public Produto edita(Long id){
		return dao.carrega(id);
	}
	
    @Restrito 
    @Put("/produtos/{produto.id}")
    public void altera(Produto produto){
		dao.atualiza(produto);
		result.redirectTo(this).lista();
	}
	
    @Restrito
    @Delete("/produtos/{id}")
	public void remove(Long id){
		Produto produto = dao.carrega(id);
		dao.remove(produto);
		result.redirectTo(ProdutosController.class).lista();
	}
	
    @Restrito
	@Get("produtos/novo")
	public void formulario(){}
	
	//@Get("produtos/busca")
	//public void busca(){}
	
	//@Get("produtos/busca")
	public List<Produto>busca(String nome){
		result.include("nome",nome);
		return dao.busca(nome);
	}
	
	
	@Get("produtos/busca.json")
	public void buscaJson(String q){
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id", "descricao").serialize();
	}
}
