package com.example.websocket_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.config.WebsocketConfig;
import com.example.controller.WebsocketController;
import com.example.manager.WebsocketSessionManager;


@SpringBootApplication
@ComponentScan(basePackageClasses = WebsocketConfig.class)
@ComponentScan(basePackageClasses = WebsocketController.class)
@ComponentScan(basePackageClasses = WebsocketSessionManager.class)
public class WebsocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketDemoApplication.class, args);
	}
}
