package ru.ibragimov.clientcontactservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ibragimov.clientcontactservice.entity.Contact;

/**
 * Репозиторий для доступа к контактной информации клиентов.
 *
 * Предоставляет стандартные CRUD-операции для сущности Contact
 * благодаря наследованию от JpaRepository.
 *
 * Работает с сущностью Contact и её ID типа Long.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
