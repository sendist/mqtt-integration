package com.example.demo.service;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttPersistenceException;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

  private final MqttAsyncClient mqttClient;

  public MqttService(MqttAsyncClient mqttClient) {
    this.mqttClient = mqttClient;
    mqttClient.setCallback(new MqttCallback() {
      @Override
      public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived from topic " + topic + ": " + new String(message.getPayload()));
      }

      @Override
      public void mqttErrorOccurred(MqttException exception) {
        System.out.println("An error occurred: " + exception.getMessage());
      }

      @Override
      public void deliveryComplete(IMqttToken token) {
        System.out.println("Delivery complete");
      }

      @Override
      public void connectComplete(boolean reconnect, String serverURI) {
        System.out.println("Connected to " + serverURI);
      }

      @Override
      public void authPacketArrived(int arg0, MqttProperties arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authPacketArrived'");
      }

      @Override
      public void disconnected(MqttDisconnectResponse arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnected'");
      }
    });

  }

  public void subscribe(String topic) throws MqttException {
    int qos = 0;
    IMqttToken token = mqttClient.subscribe(topic, qos);
    token.waitForCompletion();
    System.out.println("Subscribed to " + topic);
  }

  public void publish(String topic, String payload)
      throws MqttException, MqttPersistenceException, org.eclipse.paho.mqttv5.common.MqttException {
    MqttMessage message = new MqttMessage();
    message.setPayload(payload.getBytes());
    mqttClient.publish(topic, message);
  }
}