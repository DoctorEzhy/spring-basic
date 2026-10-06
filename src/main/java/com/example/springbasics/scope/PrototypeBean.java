package com.example.springbasics.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Scope "prototype": НОВЫЙ экземпляр при каждом запросе бина у контейнера.
 * Создаётся не при старте, а только в момент getBean().
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {

    private static final AtomicInteger INSTANCES = new AtomicInteger();
    private final int instanceNumber = INSTANCES.incrementAndGet();

    public PrototypeBean() {
        System.out.println("[PrototypeBean] создан экземпляр №" + instanceNumber);
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }
}
