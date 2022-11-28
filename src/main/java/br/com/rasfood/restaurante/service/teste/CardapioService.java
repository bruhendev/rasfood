package br.com.rasfood.restaurante.service.teste;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.dao.CardapioDao;
import br.com.rasfood.restaurante.util.CargaDeDatosUtils;
import br.com.rasfood.restaurante.util.JPAUtil;

public class CardapioService {
	
	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		entityManager.getTransaction().begin();
		CargaDeDatosUtils.cadastrarCategoria(entityManager);
		CargaDeDatosUtils.cadastrarProdutoCardapio(entityManager);
		CardapioDao cardapioDao = new CardapioDao(entityManager);
		System.out.println("------------ " + cardapioDao.consultarPorNome("salmão"));
		entityManager.close();
		
	}

}
