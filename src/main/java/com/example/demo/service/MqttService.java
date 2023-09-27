package com.example.demo.service;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttPersistenceException;
import org.springframework.stereotype.Service;


@Service
public class MqttService {


  private final MqttAsyncClient mqttClient;

  public MqttService(MqttAsyncClient mqttClient) {
    this.mqttClient = mqttClient;
  }

  public void subscribe(String topic) throws MqttException {
      int qos = 1; 
      IMqttToken token = mqttClient.subscribe(topic, qos);
      token.waitForCompletion();
      System.out.println("Subscribed to " + topic);
  }

  public void publish(String topic, String payload) throws MqttException, MqttPersistenceException, org.eclipse.paho.mqttv5.common.MqttException {
    MqttMessage message = new MqttMessage();
    message.setPayload(payload.getBytes());
    mqttClient.publish(topic, message);
  }
}