package com.example.demo;

import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.service.MqttService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        MqttAsyncClient mqttClient = context.getBean(MqttAsyncClient.class);
		MqttService mqttService = new MqttService(mqttClient);

		// Subscribe to a topic
		String subsTopic = "topic1"; // Replace with your topic
		try {
			mqttService.subscribe(subsTopic);
		} catch (MqttException e) {
			e.printStackTrace();
		}

		// Publish a message
		String topic = "topic5";
		String payload = "TEst topic 5!";
		try {
			mqttService.publish(topic, payload);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
