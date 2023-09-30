package com.protonmail.landrevillejf.cognos.categories.api.kafka;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Revision(
        date = "2023-09-29",
        revision = 1,
        comments = "KafkaConsumerService"
)
@Profile("production")
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "category-events", groupId = "your-consumer-group-id")
    public void listenToCategoryEvents(String message) {
        // Process the received message
        System.out.println("Received message: " + message);
    }
}
