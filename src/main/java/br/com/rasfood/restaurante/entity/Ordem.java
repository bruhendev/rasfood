package br.com.rasfood.restaurante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordens")
public class Ordem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(name = "data_de_criacao")
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
	private List<OrdensCardapio> ordensCardapioList = new ArrayList<>();

	public Ordem() {
	}

	public Ordem(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addOrdensCardapio(OrdensCardapio ordensCardapio) {
		ordensCardapio.setOrdem(this);
		this.ordensCardapioList.add(ordensCardapio);
		this.valorTotal =  valorTotal.add(ordensCardapio.getValorDeResgistro().multiply(BigDecimal.valueOf(ordensCardapio.getQuantidade())));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<OrdensCardapio> getOrdensCardapioList() {
		return ordensCardapioList;
	}

	public void setOrdensCardapioList(List<OrdensCardapio> ordensCardapioList) {
		this.ordensCardapioList = ordensCardapioList;
	}

	@Override
	public String toString() {
		return "Ordem [id=" + id + ", valorTotal=" + valorTotal + ", dataDeCriacao=" + dataDeCriacao + ", cliente="
				+ cliente + ", ordensCardapioList=" + ordensCardapioList + "]";
	}

	

}
