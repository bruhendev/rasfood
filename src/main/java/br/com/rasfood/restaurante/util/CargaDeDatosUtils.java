package br.com.rasfood.restaurante.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasfood.restaurante.dao.CardapioDao;
import br.com.rasfood.restaurante.dao.CategoriaDao;
import br.com.rasfood.restaurante.entity.Cardapio;
import br.com.rasfood.restaurante.entity.Categoria;

public class CargaDeDatosUtils {
	
	public static void cadastrarCategoria(EntityManager entityManager) {
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);

		Categoria pratoPrincipal = new Categoria("Prato Principal");
		Categoria entradas = new Categoria("Entradas");
		Categoria saladas = new Categoria("Saladas");
		
		categoriaDao.cadastrar(pratoPrincipal);
		entityManager.flush();
		categoriaDao.cadastrar(entradas);
		entityManager.flush();
		categoriaDao.cadastrar(saladas);
		entityManager.flush();
		entityManager.clear();
	}

	public static void cadastrarProdutoCardapio(EntityManager entityManager) {
		CardapioDao cardapioDao = new CardapioDao(entityManager);
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);

		List<Categoria> categorias = categoriaDao.consultarTodos();

		Cardapio risoto = new Cardapio("Risoto de frutos do mar", "Risoto feito com lula, camarão e mariscos", true,
				BigDecimal.valueOf(98.00), LocalDateTime.now(), categorias.get(0));
		Cardapio salmao = new Cardapio("Salmão", "Salmão ao molho de tomate", true,
				BigDecimal.valueOf(105.00), LocalDateTime.now(), categorias.get(0));
		Cardapio saladaDeCove = new Cardapio("Salada de Cove", "Salada de cove refogada", true,
				BigDecimal.valueOf(8.00), LocalDateTime.now(), categorias.get(2));
		Cardapio pudin = new Cardapio("Pudin", "Pudin classico", true,
				BigDecimal.valueOf(98.00), LocalDateTime.now(), categorias.get(1));

		cardapioDao.cadastrar(risoto);
		cardapioDao.cadastrar(salmao);
		cardapioDao.cadastrar(saladaDeCove);
		cardapioDao.cadastrar(pudin);
		entityManager.getTransaction().commit();
		entityManager.clear();

	}
}
