package ru.ibragimov.clientcontactservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ibragimov.clientcontactservice.entity.Client;
import ru.ibragimov.clientcontactservice.entity.Contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15:///clientsdb",
        "spring.datasource.username=admin",
        "spring.datasource.password=admin123",
        "spring.jpa.hibernate.ddl-auto=update"
})
class ClientRepositoryIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSaveAndFindClient() {
        Contact contact = new Contact(null, "1234567890", "user@example.com");
        Client saved = clientRepository.save(new Client(null, "Тест", "Пользователь", contact));

        Client found = clientRepository.findById(saved.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Тест", found.getName());
        assertEquals("user@example.com", found.getContact().getEmail());
    }
}
