package br.com.quim.sender.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.quim.sender.component.OrderQueueSender;

@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderQueueSender orderQueueSender;

    
    //POST method to create a message in Rabbit
    //Using POSTMAN POST http://localhost:8080/orders/ to send a text as part of the message
    //Access http://localhost:15672 with user password guest to see the messages in the console
    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody String order) {
        orderQueueSender.send(order);
        System.out.print("Postando mensagem");
    }

}
