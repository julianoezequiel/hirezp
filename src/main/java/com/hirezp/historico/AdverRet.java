package com.hirezp.historico;

public class AdverRet {

	String nome;
	String id;
	Integer total = 0;
	Integer vit = 0;
	Integer der = 0;

	Integer vitCont = 0;
	Integer derContr = 0;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void addV() {
		this.vit++;
	}

	public void addD() {
		this.der++;
	}

	public Integer getVitCont() {
		return vitCont;
	}

	public void setVitCont(Integer vitCont) {
		this.vitCont = vitCont;
	}

	public Integer getDerContr() {
		return derContr;
	}

	public void setDerContr(Integer derContr) {
		this.derContr = derContr;
	}

	public void addVC() {
		this.vitCont++;
	}

	public void addDC() {
		this.derContr++;
	}

}
