package com.hirezp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "proxy")
public class ProxyProperties {

	private String user = "toppontoweb";
	private String password = "TpW@T04";
	private String host = "10.0.0.5";
	private Integer port = 8080;
	private Boolean habilitado = false;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
