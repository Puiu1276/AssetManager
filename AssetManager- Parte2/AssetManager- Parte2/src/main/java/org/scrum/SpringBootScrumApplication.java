package org.scrum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//
import java.util.logging.Logger;
//
import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
public class SpringBootScrumApplication extends SpringBootServletInitializer
{
	private static Logger logger = Logger.getLogger(SpringBootScrumApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("Loading ... :: SpringBoot - ScrumApplication Default Settings ...  ");
		SpringApplication.run(SpringBootScrumApplication.class, args);
	}

	// JMS Server Configuration
	@Bean
	public JmsListenerContainerFactory<?> connectionFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all auto-configured defaults to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some settings if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean // To allow external connection to embedded JMS Server
	public ArtemisConfigurationCustomizer artemisConfigurationCustomizer() {
		return configuration -> {
			try {
				configuration.addAcceptorConfiguration("remote", "tcp://localhost:61616");
				//configuration.addAcceptorConfiguration("remote", "tcp://localhost:7171");
			} catch (Exception e) {
				throw new RuntimeException("Failed to add netty transport acceptor to artemis instance");
			}
		};
	}



	// Using persistence.xml
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("MSD_LOCAL");
		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}



/**
 * Spring JMS (from Spring Integration)
 * https://docs.spring.io/spring-framework/reference/integration/jms.html
 * https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.jms
 *
 * Spring AMQP
 * https://spring.io/projects/spring-amqp
 * https://www.baeldung.com/spring-amqp
 * https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.jms.activemq
 * https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging
 */