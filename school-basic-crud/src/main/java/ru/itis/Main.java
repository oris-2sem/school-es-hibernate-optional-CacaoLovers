package ru.itis;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.itis.app.Client;
import ru.itis.config.DatabaseConfig;


@AllArgsConstructor
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        Client client = (Client) applicationContext.getBean(Client.class);
        client.work();
    }
}
