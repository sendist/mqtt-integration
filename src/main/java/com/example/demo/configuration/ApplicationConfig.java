package com.example.demo.configuration;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptionsBuilder;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.configuration.properties.MqttProp;

@Configuration
public class ApplicationConfig {

  @Bean
  public MqttAsyncClient mqttAsyncClient(MqttProp prop) throws MqttException {
      var options = new MqttConnectionOptionsBuilder()
          .automaticReconnect(true)
          .cleanStart(true)
          .connectionTimeout(30)
          .username(prop.getUsername())
          .password(prop.getPasswordBytes())
          .build();

      var client = new MqttAsyncClient(prop.getBrokerAddress(), prop.getClientId());
      IMqttToken token = client.connect(options);
      token.waitForCompletion();

      return client;
  }


}
