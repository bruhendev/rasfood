package br.com.rasfood.restaurante.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordens_cardapio")
public class OrdensCardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Ordem ordem;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cardapio cardapio;

	@Column(name = "valor_de_registro")
	private BigDecimal valorDeResgistro;

	private Integer quantidade;

	public OrdensCardapio() {
	}

	public OrdensCardapio(Cardapio cardapio, Integer quantidade) {
		this.cardapio = cardapio;
		this.quantidade = quantidade;
		this.valorDeResgistro = cardapio.getValor();
	}

	public BigDecimal getValorDeResgistro() {
		return valorDeResgistro;
	}

	public void setValorDeResgistro(BigDecimal valorDeResgistro) {
		this.valorDeResgistro = valorDeResgistro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ordem getOrdem() {
		return ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "OrdensCardapio [id=" + id + ", cardapio=" + cardapio + ", valorDeResgistro=" + valorDeResgistro
				+ ", quantidade=" + quantidade + "]";
	}

	
}
