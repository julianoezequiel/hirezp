package com.hirezp.historico;

public class MapsRet {

	String nome;
	String nomePT;
	Integer total;
	Integer vit = 0;
	Integer der = 0;

	public MapsRet() {
	}

	public MapsRet(String nome, String nomePt) {
		this.nome = nome;
		this.nomePT = nomePt;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePT() {
		return nomePT;
	}

	public void setNomePT(String nomePT) {
		this.nomePT = nomePT;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getVit() {
		return vit;
	}

	public void setVit(Integer vit) {
		this.vit = vit;
	}

	public Integer getDer() {
		return der;
	}

	public void setDer(Integer der) {
		this.der = der;
	}

	public void addVit() {
		this.vit++;
	}

	public void addDer() {
		this.der++;
	}

}
