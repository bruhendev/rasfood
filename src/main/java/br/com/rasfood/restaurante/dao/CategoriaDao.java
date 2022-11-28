package br.com.rasfood.restaurante.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.entity.Categoria;

public class CategoriaDao {

	private EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Categoria categoria) {
		this.entityManager.persist(categoria);
	}

	public Categoria consultarPorId(final Integer id) {
		return this.entityManager.find(Categoria.class, id);
	}
	
	public List<Categoria> consultarTodos() {
		String sql = "select c from Categoria c";
		return this.entityManager.createQuery(sql, Categoria.class).getResultList();
	}

	public Categoria atualizar(final Categoria categoria) {
		return this.entityManager.merge(categoria);
	}

	public void remover(final Categoria categoria) {
		this.entityManager.remove(categoria);
	}
}
