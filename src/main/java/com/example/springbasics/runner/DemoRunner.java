package com.example.springbasics.runner;

import com.example.springbasics.order.OrderService;
import com.example.springbasics.scope.PrototypeBean;
import com.example.springbasics.scope.SingletonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {

    private final ApplicationContext context;
    private final OrderService orderService;

    @Autowired
    public DemoRunner(ApplicationContext context, OrderService orderService) {
        this.context = context;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {
        demoDependencyChain();
        demoQualifier();
        demoScopes();
    }

    private void demoDependencyChain() {
        System.out.println("\n=== 1. Цепочка зависимостей ===");
        System.out.println(orderService.placeOrder("Книга", 2));
        System.out.println(orderService.placeOrder("Ноутбук", 5));
    }

    private void demoQualifier() {
        System.out.println("\n=== 2. @Primary и @Qualifier ===");
        orderService.alertUrgent("Срочно: проверьте склад!");
    }

    private void demoScopes() {
        System.out.println("\n=== 3. Singleton и prototype ===");

        SingletonBean s1 = context.getBean(SingletonBean.class);
        SingletonBean s2 = context.getBean(SingletonBean.class);
        System.out.println("singleton: №" + s1.getInstanceNumber() + " и №" + s2.getInstanceNumber()
                + ", один и тот же объект? " + (s1 == s2));

        PrototypeBean p1 = context.getBean(PrototypeBean.class);
        PrototypeBean p2 = context.getBean(PrototypeBean.class);
        System.out.println("prototype: №" + p1.getInstanceNumber() + " и №" + p2.getInstanceNumber()
                + ", один и тот же объект? " + (p1 == p2));
    }
}
