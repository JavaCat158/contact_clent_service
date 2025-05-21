package ru.ibragimov.clientcontactservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ibragimov.clientcontactservice.entity.Client;

/**
 * Репозиторий для доступа к данным клиентов.
 *
 * Наследуется от JpaRepository, что предоставляет
 * базовые CRUD-операции без необходимости реализации вручную.
 *
 * Работает с сущностью Client и её идентификатором типа Long.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
