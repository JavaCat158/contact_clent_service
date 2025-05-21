package ru.ibragimov.clientcontactservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ibragimov.clientcontactservice.dto.ClientDTO;
import ru.ibragimov.clientcontactservice.entity.Client;
import ru.ibragimov.clientcontactservice.mapper.ClientMapper;
import ru.ibragimov.clientcontactservice.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для управления клиентами.
 *
 * Предоставляет API для создания, получения, обновления и удаления клиентов.
 * Все входные и выходные данные представлены через DTO-объекты.
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Создание нового клиента.
     *
     * @param client DTO объекта клиента, прошедший валидацию
     * @return созданный клиент в виде DTO
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO client) {
        Client newClient = clientService.create(ClientMapper.toEntity(client));
        return ResponseEntity.ok(ClientMapper.toDTO(newClient));
    }

    /**
     * Получение списка всех клиентов.
     *
     * @return список клиентов в формате DTO
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAllClients() {
        List<ClientDTO> clients = clientService.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clients);
    }

    /**
     * Получение клиента по его идентификатору.
     *
     * @param id идентификатор клиента
     * @return клиент, если найден, или 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        return clientService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Обновление существующего клиента.
     *
     * @param clientDTO обновлённые данные клиента
     * @return обновлённый клиент в формате DTO
     */
    @PutMapping
    public ResponseEntity<ClientDTO> updateClient(@RequestBody @Valid ClientDTO clientDTO) {
        Client clientUpdated = clientService.update(ClientMapper.toEntity(clientDTO));
        return ResponseEntity.ok(ClientMapper.toDTO(clientUpdated));
    }

    /**
     * Удаление клиента по его идентификатору.
     *
     * @param id идентификатор клиента
     * @return пустой ответ с кодом 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
