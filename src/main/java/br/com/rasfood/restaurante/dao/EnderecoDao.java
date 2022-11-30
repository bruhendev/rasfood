package br.com.rasfood.restaurante.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.rasfood.restaurante.entity.Cliente;
import br.com.rasfood.restaurante.entity.Endereco;
import br.com.rasfood.restaurante.vo.ClienteVo;

public class EnderecoDao {

    private EntityManager entityManager;

    public EnderecoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(final Endereco endereco) {
        this.entityManager.persist(endereco);
    }

    public Endereco consultarPorId(final Integer id) {
        return this.entityManager.find(Endereco.class, id);
    }

    public List<Endereco> consultarTodos() {
        String sql = "select o from Endereco o";
        return this.entityManager.createQuery(sql, Endereco.class).getResultList();
    }

    public Endereco atualizar(final Endereco endereco) {
        return this.entityManager.merge(endereco);
    }

    public void remover(final Endereco endereco) {
        this.entityManager.remove(endereco);
    }

    public List<ClienteVo> consultarClientes(String estado, String cidade, String rua) {
        String jpql = "select new br.com.rasfood.restaurante.vo.ClienteVo(e.cliente.cpf, e.cliente.nome) " +
                "from Endereco e where 1=1 ";

        if(Objects.nonNull(estado)) {
            jpql = jpql.concat("and upper(e.estado) = upper(:estado) ");
        }

        if(Objects.nonNull(cidade)) {
            jpql = jpql.concat("and upper(e.cidade) = upper(:cidade) ");
        }

        if(Objects.nonNull(rua)) {
            jpql = jpql.concat("and upper(e.rua) = upper(:rua) ");
        }

        var typedQuery = this.entityManager.createQuery(jpql, ClienteVo.class);

        if(Objects.nonNull(estado)) {
            typedQuery.setParameter("estado", estado);
        }

        if(Objects.nonNull(cidade)) {
            typedQuery.setParameter("cidade", cidade);
        }

        if(Objects.nonNull(rua)) {
            typedQuery.setParameter("rua", rua);
        }

        return typedQuery.getResultList();
    }

    public List<ClienteVo> consultarClientesCriteria(String estado, String cidade, String rua) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(root.get("cliente").get("cpf"), root.get("cliente").get("nome"));
        Predicate predicate = builder.and();

        if(Objects.nonNull(estado)) {
            predicate = builder.and(predicate, builder.equal(builder.upper(root.get("estado")), estado.toUpperCase()));
        }

        if(Objects.nonNull(cidade)) {
            predicate = builder.and(predicate, builder.equal(builder.upper(root.get("cidade")), cidade.toUpperCase()));
        }

        if(Objects.nonNull(rua)) {
            predicate = builder.and(predicate, builder.equal(builder.upper(root.get("rua")), rua.toUpperCase()));
        }

        criteriaQuery.where(predicate);
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }
}
