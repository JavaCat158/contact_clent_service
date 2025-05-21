package ru.ibragimov.clientcontactservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность Contact представляет контактную информацию клиента.
 * Отображается в таблицу "contacts" в базе данных.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "contacts")
public class Contact {

    /**
     * Уникальный идентификатор контакта.
     * Генерируется автоматически (auto-increment).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Телефон клиента.
     */
    private String phone;

    /**
     * Адрес электронной почты клиента.
     */
    private String email;
}
