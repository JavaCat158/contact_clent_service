package ru.ibragimov.clientcontactservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность клиента, отображающаяся в таблицу "clients" в базе данных.
 *
 * Содержит имя, фамилию и связь один-к-одному с контактной информацией (Contact).
 */
@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    /**
     * Уникальный идентификатор клиента.
     * Генерируется автоматически (auto-increment).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя клиента.
     */
    private String name;

    /**
     * Фамилия клиента.
     */
    private String lastName;

    /**
     * Контактная информация, связанная с клиентом.
     * Отношение один-к-одному.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;
}
