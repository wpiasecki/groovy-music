package br.com.groovymusic.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QueryDinamica<T> {

	private EntityManager em;
	private String projecao;
	private Class<T> tipoRetorno;
	private List<Parametro> parametros = new ArrayList<>();

	static class Parametro {
		String query;
		String chave;
		Object valor;

		public Parametro(String query, String chave, Object valor) {
			this.query = query;
			this.chave = chave;
			this.valor = valor;
		}
	}

	public QueryDinamica(EntityManager em, String projecao, Class<T> tipoRetorno) {
		this.em = em;
		this.projecao = projecao;
		this.tipoRetorno = tipoRetorno;
	}

	public QueryDinamica<T> concat(Boolean condicao, String query, String chave, Object valor) {
		if (condicao) {
			parametros.add(new Parametro(query, chave, valor));
		}
		return this;
	}

	public TypedQuery<T> toQuery() {
		TypedQuery<T> query = em.createQuery(
				projecao + String.join(" ", parametros.stream().map(a -> a.query).collect(Collectors.toList())),
				tipoRetorno);
		parametros.forEach(param -> query.setParameter(param.chave, param.valor));
		return query;
	}

}
