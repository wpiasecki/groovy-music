package br.com.groovymusic.config;

import com.google.inject.AbstractModule;

import br.com.groovymusic.config.EntityManagerImpl;

public class DITest extends AbstractModule {

	@Override
	protected void configure() {
		bind(EntityManagerImpl.class).to(EntityManagerTest.class);
	}

}
