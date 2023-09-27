package com.example.demo.controller;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MqttService;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private final MqttService mqttService;

    @Autowired
    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody String message) {
        try {
            mqttService.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam String topic) {
        try {
            mqttService.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}