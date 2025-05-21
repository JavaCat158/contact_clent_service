package ru.ibragimov.clientcontactservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ibragimov.clientcontactservice.entity.Contact;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15:///clientsdb",
        "spring.datasource.username=admin",
        "spring.datasource.password=admin123",
        "spring.jpa.hibernate.ddl-auto=update"
})
class ContactRepositoryIntegrationTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testSaveAndFindContact() {
        Contact contact = new Contact(null, "89001234567", "contact@mail.com");
        Contact saved = contactRepository.save(contact);

        Contact found = contactRepository.findById(saved.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("contact@mail.com", found.getEmail());
        assertEquals("89001234567", found.getPhone());
    }
}
