package ru.ibragimov.clientcontactservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO (Data Transfer Object) для передачи данных клиента между слоями приложения.
 *
 * Содержит поля клиента с аннотациями валидации:
 * - имя
 * - фамилия
 * - контактная информация (вложенный DTO)
 */
@Data
public class ClientDTO {

    /**
     * Уникальный идентификатор клиента.
     * Может быть null при создании нового клиента.
     */
    private Long id;

    /**
     * Имя клиента.
     * Обязательно для заполнения.
     */
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    /**
     * Фамилия клиента.
     * Обязательно для заполнения.
     */
    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;

    /**
     * Контактная информация клиента.
     * Обязательно для заполнения и должна быть валидным объектом ContactDTO.
     */
    @NotNull(message = "Контакт обязателен")
    private ContactDTO contact;
}
