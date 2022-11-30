package br.com.rasfood.restaurante.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.entity.Cliente;

public class ClienteDao {
	private EntityManager entityManager;

	public ClienteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(final Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public Cliente consultarPorId(final Integer id) {
		return this.entityManager.find(Cliente.class, id);
	}
	
	public List<Cliente> consultarTodos() {
		String sql = "select c from Cliente c";
		return this.entityManager.createQuery(sql, Cliente.class).getResultList();
	}

	public Cliente atualizar(final Cliente cliente) {
		return this.entityManager.merge(cliente);
	}

	public void remover(final Cliente cliente) {
		this.entityManager.remove(cliente);
	}

	public List<Cliente> consultarPorNOme(final String nome) {
		String jpql = "select c from Cliente c where upper(c.nome) like upper(:nome) ";
		return this.entityManager.createQuery(jpql, Cliente.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
}
