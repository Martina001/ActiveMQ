package com.martina.springactivemq.producer;

import javax.jms.Message;

public interface ProducerService {
    void sendMessage(String message);
}
