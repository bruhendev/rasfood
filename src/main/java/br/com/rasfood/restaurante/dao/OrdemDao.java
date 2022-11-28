package br.com.rasfood.restaurante.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.entity.Ordem;

public class OrdemDao {

	private EntityManager entityManager;

	public OrdemDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Ordem ordem) {
		this.entityManager.persist(ordem);
	}

	public Ordem consultarPorId(final Integer id) {
		return this.entityManager.find(Ordem.class, id);
	}
	
	public List<Ordem> consultarTodos() {
		String sql = "select o from Ordem o";
		return this.entityManager.createQuery(sql, Ordem.class).getResultList();
	}

	public Ordem atualizar(final Ordem ordem) {
		return this.entityManager.merge(ordem);
	}

	public void remover(final Ordem ordem) {
		this.entityManager.remove(ordem);
	}
}
