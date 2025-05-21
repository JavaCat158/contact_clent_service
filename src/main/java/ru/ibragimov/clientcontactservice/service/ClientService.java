package ru.ibragimov.clientcontactservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ibragimov.clientcontactservice.entity.Client;
import ru.ibragimov.clientcontactservice.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для работы с клиентами.
 *
 * Обеспечивает бизнес-логику и взаимодействие между контроллерами и слоем хранения данных.
 * Выполняет базовые CRUD-операции над сущностью  Client.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Создаёт нового клиента.
     *
     * @param client объект клиента для сохранения
     * @return сохранённый клиент
     * @throws IllegalArgumentException если клиент равен null
     */
    public Client create(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Клиент не оформлен");
        }
        return clientRepository.save(client);
    }

    /**
     * Получает список всех клиентов.
     *
     * @return список всех клиентов
     */
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Получает клиента по его идентификатору.
     *
     * @param id идентификатор клиента
     * @return объект клиента, обёрнутый в  Optional
     */
    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Обновляет данные клиента.
     *
     * @param updateClient клиент с обновлёнными данными
     * @return обновлённый и сохранённый клиент
     * @throws RuntimeException если клиент с таким ID не найден
     */
    public Client update(Client updateClient) {
        return clientRepository.findById(updateClient.getId())
                .map(client -> {
                    client.setName(updateClient.getName());
                    client.setLastName(updateClient.getLastName());
                    client.setContact(updateClient.getContact());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }

    /**
     * Удаляет клиента по его идентификатору.
     *
     * @param id идентификатор клиента
     */
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
