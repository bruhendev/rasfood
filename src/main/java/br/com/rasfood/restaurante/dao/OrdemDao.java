package br.com.rasfood.restaurante.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.entity.Ordem;
import br.com.rasfood.restaurante.vo.ItensPrincipaisVo;

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

	public List<ItensPrincipaisVo> consultarItensMaisVendidos() {
		String sql = "select new br.com.rasfood.restaurante.vo.ItensPrincipaisVo(c.nome, sum(oc.quantidade)) from Ordem o "
				+ "join OrdensCardapio oc on o.id = oc.cardapio.id " + "join oc.cardapio c " + "group by c.nome "
				+ "order by sum(oc.quantidade) desc";
		return this.entityManager.createQuery(sql, ItensPrincipaisVo.class).getResultList();
	}
	
	public Ordem joinFetchCliente(final Integer id) {
		String jpql = "select o from Ordem o join fetch o.cliente where o.id = :id ";
		return this.entityManager.createQuery(jpql, Ordem.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
