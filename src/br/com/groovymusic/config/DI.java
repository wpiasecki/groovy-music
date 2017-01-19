package br.com.groovymusic.config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.groovymusic.config.DITest;

public class DI extends AbstractModule {
	
	public static boolean TEST = false;

	public static Injector injector() {
		return Guice.createInjector(TEST ? new DITest() : new DI());
	}
	
	@Override
	protected void configure() {
		bind(EntityManagerImpl.class).to(EntityManagerProducao.class);
	}
	
}
