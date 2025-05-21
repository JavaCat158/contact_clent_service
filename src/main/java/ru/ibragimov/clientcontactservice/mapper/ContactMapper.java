package ru.ibragimov.clientcontactservice.mapper;

import ru.ibragimov.clientcontactservice.dto.ContactDTO;
import ru.ibragimov.clientcontactservice.entity.Contact;

/**
 * Утилитный класс для преобразования между сущностью Contact
 * и DTO-объектом ContactDTO.
 *
 * Используется для передачи данных между слоями контроллера и базы данных,
 * избегая прямой работы с сущностями в REST-слое.
 */
public class ContactMapper {

    /**
     * Преобразует сущность Contact в объект DTO.
     *
     * @param contact объект сущности Contact
     * @return объект ContactDTO, содержащий те же данные
     */
    public static ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        return dto;
    }

    /**
     * Преобразует объект ContactDTO в сущность Contact.
     *
     * @param dto DTO объект, содержащий данные контакта
     * @return сущность Contact, готовая к сохранению в базу данных
     */
    public static Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setId(dto.getId());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        return contact;
    }
}
