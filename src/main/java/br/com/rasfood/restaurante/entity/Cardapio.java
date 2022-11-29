package br.com.rasfood.restaurante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cardapio")
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String descricao;

	private boolean disponivel;

	private BigDecimal valor;

	@Column(name = "data_de_registro")
	private LocalDateTime dataDeRegistro = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	public Cardapio() {
	}

	public Cardapio(String nome, String descricao, boolean disponivel, BigDecimal valor, LocalDateTime dataDeRegistro,
			Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.disponivel = disponivel;
		this.valor = valor;
		this.dataDeRegistro = dataDeRegistro;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Cardapio [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", disponivel=" + disponivel
				+ ", valor=" + valor + ", dataDeRegistro=" + dataDeRegistro + ", categoria=" + categoria.getNome()
				+ "]";
	}

}
