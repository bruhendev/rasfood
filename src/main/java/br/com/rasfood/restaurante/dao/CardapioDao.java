package br.com.rasfood.restaurante.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.entity.Cardapio;

public class CardapioDao {

	private EntityManager entityManager;

	public CardapioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Cardapio cardapio) {
		this.entityManager.persist(cardapio);
	}

	public List<Cardapio> consultarTodos() {
		String sql = "select c from Cardapio c";
		return this.entityManager.createQuery(sql, Cardapio.class).getResultList();
	}

	public List<Cardapio> consultarPorValor(BigDecimal valor) {
		String Jpql = "select c from Cardapio c where c.valor = :valor";
		return this.entityManager.createQuery(Jpql, Cardapio.class).setParameter("valor", valor).getResultList();
	}

	public Cardapio consultarPorNome(String nome) {
		try {
			String Jpql = "select c from Cardapio c where upper(c.nome) = upper(:nome)";
			return this.entityManager.createQuery(Jpql, Cardapio.class).setParameter("nome", nome).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Cardapio consultarPorId(final Integer id) {
		return this.entityManager.find(Cardapio.class, id);
	}

	public Cardapio atualizar(final Cardapio cardapio) {
		return this.entityManager.merge(cardapio);
	}

	public void remover(final Cardapio cardapio) {
		this.entityManager.remove(cardapio);
	}
}
