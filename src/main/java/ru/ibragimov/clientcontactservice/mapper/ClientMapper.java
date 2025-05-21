package ru.ibragimov.clientcontactservice.mapper;

import ru.ibragimov.clientcontactservice.dto.ClientDTO;
import ru.ibragimov.clientcontactservice.entity.Client;

/**
 * Утилитный класс для преобразования между сущностью Client
 * и DTO-объектом ClientDTO.
 *
 * Используется для отделения слоя представления (DTO) от бизнес-логики и базы данных (Entity).
 */
public class ClientMapper {

    /**
     * Преобразует сущность Client в DTO-объект ClientDTO.
     *
     * @param client объект сущности клиента
     * @return объект DTO, содержащий те же данные
     */
    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setContact(ContactMapper.toDTO(client.getContact()));
        return clientDTO;
    }

    /**
     * Преобразует DTO-объект ClientDTO в сущность Client.
     *
     * @param clientDTO DTO клиента
     * @return объект сущности, готовый к сохранению в базу данных
     */
    public static Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setContact(ContactMapper.toEntity(clientDTO.getContact()));
        return client;
    }
}
