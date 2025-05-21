package ru.ibragimov.clientcontactservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO (Data Transfer Object) для передачи контактной информации клиента.
 *
 * Используется при создании и обновлении клиентов и их контактов.
 * Включает валидацию полей через аннотации Jakarta Bean Validation.
 */
@Data
public class ContactDTO {

    /**
     * Идентификатор контакта.
     * Может быть null при создании нового контакта.
     */
    private Long id;

    /**
     * Телефон клиента.
     * Не должен быть пустым или состоять из пробелов.
     */
    @NotBlank(message = "Поле 'phone' не должно быть пустым")
    private String phone;

    /**
     * Email клиента.
     * Обязательно для заполнения и должен быть валидным адресом электронной почты.
     */
    @NotBlank(message = "Поле 'email' не должно быть пустым")
    @Email(message = "Некорректный Email")
    private String email;
}
