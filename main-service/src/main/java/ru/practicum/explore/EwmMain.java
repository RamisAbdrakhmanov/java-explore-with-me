package ru.practicum.explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.practicum.explore.client.StatsClient;

@SpringBootApplication
@Import({StatsClient.class})
public class EwmMain {

    public static void main(String[] args) {
        SpringApplication.run(EwmMain.class, args);
    }

}
