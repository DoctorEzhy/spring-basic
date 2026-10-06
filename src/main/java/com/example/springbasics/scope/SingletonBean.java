package com.example.springbasics.scope;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Scope "singleton" (по умолчанию): на весь контейнер создаётся ОДИН экземпляр.
 * Сколько раз ни вызывай getBean(), вернётся один и тот же объект.
 * Создаётся сразу при старте приложения.
 */
@Component
public class SingletonBean {

    private static final AtomicInteger INSTANCES = new AtomicInteger();
    private final int instanceNumber = INSTANCES.incrementAndGet();

    public SingletonBean() {
        System.out.println("[SingletonBean] создан экземпляр №" + instanceNumber);
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }
}
