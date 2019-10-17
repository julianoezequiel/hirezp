package com.hirezp.historico;

public class Consulta {

	private String id;
	private String name;
	private Boolean atualizar = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(Boolean atualizar) {
		this.atualizar = atualizar;
	}

}
