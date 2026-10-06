# spring-basics-demo

Учебный проект «Основы фреймворка Spring» (КТ-1). Spring Boot 3.3, Java 17, Maven.

## Что демонстрирует проект

| Тема | Где смотреть |
|---|---|
| IoC-контейнер и регистрация бинов | `SpringBasicsApplication` (`@SpringBootApplication`), аннотации `@Component`, `@Service`, `@Repository` |
| Цепочка зависимостей из 3+ бинов | `DemoRunner` -> `OrderService` -> `OrderRepository`, `PriceCalculator`, `NotificationService` -> `NotificationSender` |
| DI через конструктор | `OrderService` (репозиторий, сервис уведомлений), `NotificationService` |
| DI через сеттер | `OrderService.setPriceCalculator(...)` |
| Scope singleton / prototype | `SingletonBean`, `PrototypeBean`, вывод в `DemoRunner` |
| Интерфейс + 2 реализации, `@Primary` и `@Qualifier` | `NotificationSender`, `EmailNotificationSender` (`@Primary`), `SmsNotificationSender` (`@Qualifier("smsSender")` в `NotificationService`) |

## Структура

```
src/main/java/com/example/springbasics
├── SpringBasicsApplication.java
├── notification
│   ├── NotificationSender.java          (интерфейс)
│   ├── EmailNotificationSender.java     (@Primary)
│   ├── SmsNotificationSender.java       (бин "smsSender")
│   └── NotificationService.java         (@Service)
├── order
│   ├── Order.java                       (модель)
│   ├── OrderRepository.java             (@Repository)
│   ├── PriceCalculator.java             (@Component)
│   └── OrderService.java                (@Service)
├── scope
│   ├── SingletonBean.java               (scope singleton)
│   └── PrototypeBean.java               (scope prototype)
└── runner
    └── DemoRunner.java                  (CommandLineRunner)
```

## Граф зависимостей бинов

```
DemoRunner
  └── OrderService
        ├── OrderRepository
        ├── PriceCalculator            (через сеттер)
        └── NotificationService
              ├── NotificationSender (по умолчанию) -> EmailNotificationSender  (@Primary)
              └── NotificationSender (@Qualifier)   -> SmsNotificationSender
```

## Сборка и запуск

```
mvn clean package
java -jar target/spring-basics-demo-1.0.0.jar
```
или `mvn spring-boot:run`.

## Ожидаемый вывод (фрагмент)

- при старте: создание бинов, в `OrderService` сначала вызывается конструктор, затем сеттер;
- блок «Цепочка зависимостей»: два заказа и email-уведомления;
- блок «@Primary и @Qualifier»: срочное сообщение уходит по SMS;
- блок «Singleton и prototype»: у singleton один и тот же объект (`true`), у prototype два разных экземпляра (`false`).
