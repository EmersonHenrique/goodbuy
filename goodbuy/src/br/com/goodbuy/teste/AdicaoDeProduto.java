package br.com.goodbuy.teste;

import org.hibernate.Session;

import br.com.goodbuy.dao.ProdutoDao;
import br.com.goodbuy.infra.CriadorDeSession;
import br.com.goodbuy.modelo.Produto;

public class AdicaoDeProduto {
	public static void main(String[] args) {
		
	    // AnnotationConfiguration configuration = new AnnotationConfiguration();
		//Session session = CriadorDeSession.getSession();     

	     Produto produto = criaProduto();	   

	   //  new ProdutoDao().salva(produto);
	}

	private static Produto criaProduto() {
		Produto produto = new Produto();
	     produto.setNome("Prateleira");
	     produto.setDescricao("Uma prateleira para colocar livros");
	     produto.setPreco(35.90);
		return produto;
	}
}
