package ru.ibragimov.clientcontactservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import ru.ibragimov.clientcontactservice.dto.ContactDTO;
import ru.ibragimov.clientcontactservice.entity.Contact;
import ru.ibragimov.clientcontactservice.mapper.ContactMapper;
import ru.ibragimov.clientcontactservice.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для управления контактной информацией клиентов.
 *
 * Предоставляет API для создания, получения, обновления и удаления контактов.
 * Все данные обрабатываются через DTO и валидируются с помощью аннотаций Bean Validation.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    /**
     * Создание нового контакта.
     *
     * @param contactDTO объект DTO с данными контакта, прошедший валидацию
     * @return созданный контакт в формате DTO
     */
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody @Valid ContactDTO contactDTO) {
        Contact newContact = contactService.createContact(ContactMapper.toEntity(contactDTO));
        return ResponseEntity.ok(ContactMapper.toDTO(newContact));
    }

    /**
     * Получение списка всех контактов.
     *
     * @return список всех сохранённых контактов в формате DTO
     */
    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts()
                .stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contacts);
    }

    /**
     * Получение контакта по его идентификатору.
     *
     * @param id идентификатор контакта
     * @return контакт, если найден, или 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> findContactById(@PathVariable Long id) {
        return contactService.getContactById(id)
                .map(ContactMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Обновление существующего контакта.
     *
     * @param contactDTO объект DTO с новыми данными контакта, прошедший валидацию
     * @return обновлённый контакт в формате DTO
     */
    @PutMapping
    public ResponseEntity<ContactDTO> updateContact(@RequestBody @Valid ContactDTO contactDTO) {
        Contact contacts = contactService.updateContact(ContactMapper.toEntity(contactDTO));
        return ResponseEntity.ok(ContactMapper.toDTO(contacts));
    }

    /**
     * Удаление контакта по его идентификатору.
     *
     * @param id идентификатор контакта
     * @return пустой ответ с кодом 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContactById(id);
        return ResponseEntity.noContent().build();
    }
}
