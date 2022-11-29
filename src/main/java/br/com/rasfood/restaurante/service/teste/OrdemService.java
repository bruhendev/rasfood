package br.com.rasfood.restaurante.service.teste;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.dao.CardapioDao;
import br.com.rasfood.restaurante.dao.ClienteDao;
import br.com.rasfood.restaurante.dao.OrdemDao;
import br.com.rasfood.restaurante.entity.Cliente;
import br.com.rasfood.restaurante.entity.Endereco;
import br.com.rasfood.restaurante.entity.Ordem;
import br.com.rasfood.restaurante.entity.OrdensCardapio;
import br.com.rasfood.restaurante.util.CargaDeDatosUtils;
import br.com.rasfood.restaurante.util.JPAUtil;

public class OrdemService {
	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
		entityManager.getTransaction().begin();
		CargaDeDatosUtils.cadastrarCategoria(entityManager);
		CargaDeDatosUtils.cadastrarProdutoCardapio(entityManager);

		CardapioDao cardapioDao = new CardapioDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);
		OrdemDao ordemDao = new OrdemDao(entityManager);

		Endereco endereco = new Endereco("00000000", "Rua Tal 255", "sem complemento", "Pará", "Belem");
		Cliente felipe = new Cliente("00665423490", "Felipe Silva Andrade");
		felipe.addEndereco(endereco);
		;
		Ordem newOrdem = new Ordem(felipe);
		newOrdem.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultarPorId(1), 2));

		clienteDao.cadastrar(felipe);
		ordemDao.cadastrar(newOrdem);

		Ordem ordem = ordemDao.joinFetchCliente(1);
		
		System.out.println(ordem.getCliente().getNome());

		entityManager.getTransaction().commit();

		entityManager.close();
	}
}
