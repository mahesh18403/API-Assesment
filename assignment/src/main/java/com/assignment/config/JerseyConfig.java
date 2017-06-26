package com.assignment.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;

import com.assignment.controllers.RequestController;

public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		registerEndPoints();
	}
	
	public void registerEndPoints() {
		register(WadlResource.class);
		register(RequestController.class);
		register(MultiPartFeature.class);
		//register(JpaRepository.class);
	}

}
