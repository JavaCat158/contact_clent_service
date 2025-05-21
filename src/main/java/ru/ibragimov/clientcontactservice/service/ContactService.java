package ru.ibragimov.clientcontactservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ibragimov.clientcontactservice.entity.Contact;
import ru.ibragimov.clientcontactservice.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления контактной информацией.
 *
 * Выполняет бизнес-логику, связанную с CRUD-операциями над сущностью  Contact.
 * Используется в контроллерах для разделения ответственности и повторного использования логики.
 */
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    /**
     * Создаёт новый контакт.
     *
     * @param contact контакт для сохранения
     * @return сохранённый контакт
     * @throws IllegalArgumentException если передан null
     */
    public Contact createContact(Contact contact) {
        if (contact != null) {
            return contactRepository.save(contact);
        }
        throw new IllegalArgumentException("Клиент не оформлен");
    }

    /**
     * Возвращает список всех сохранённых контактов.
     *
     * @return список контактов
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Возвращает контакт по его идентификатору.
     *
     * @param id идентификатор контакта
     * @return найденный контакт или пустой Optional, если не найден
     */
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    /**
     * Обновляет контактную информацию.
     *
     * @param updateContact объект с новыми значениями
     * @return обновлённый контакт
     * @throws RuntimeException если контакт не найден
     */
    public Contact updateContact(Contact updateContact) {
        return contactRepository.findById(updateContact.getId())
                .map(existing -> {
                    existing.setPhone(updateContact.getPhone());
                    existing.setEmail(updateContact.getEmail());
                    return contactRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Контакт не найден"));
    }

    /**
     * Удаляет контакт по его идентификатору.
     *
     * @param id идентификатор контакта
     */
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
