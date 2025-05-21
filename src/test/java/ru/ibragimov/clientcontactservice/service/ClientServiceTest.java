package ru.ibragimov.clientcontactservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibragimov.clientcontactservice.entity.Client;
import ru.ibragimov.clientcontactservice.entity.Contact;
import ru.ibragimov.clientcontactservice.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void create_validClient_shouldReturnSavedClient() {
        Client client = new Client(null, "Иван", "Иванов", new Contact());
        Client savedClient = new Client(1L, "Иван", "Иванов", new Contact());

        when(clientRepository.save(client)).thenReturn(savedClient);

        Client result = clientService.create(client);

        assertEquals(savedClient, result);
        verify(clientRepository).save(client);
    }

    @Test
    void create_nullClient_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> clientService.create(null));
        verify(clientRepository, never()).save(any());
    }

    @Test
    void findAll_shouldReturnListOfClients() {
        List<Client> mockClients = List.of(new Client(1L, "Анна", "Петрова", new Contact()));
        when(clientRepository.findAll()).thenReturn(mockClients);

        List<Client> result = clientService.findAll();

        assertEquals(mockClients.size(), result.size());
        assertEquals(mockClients.get(0).getName(), result.get(0).getName());
        verify(clientRepository).findAll();
    }

    @Test
    void getById_existingId_shouldReturnClient() {
        Client client = new Client(1L, "Олег", "Смирнов", new Contact());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Олег", result.get().getName());
        verify(clientRepository).findById(1L);
    }

    @Test
    void update_existingClient_shouldUpdateFields() {
        Client original = new Client(1L, "Сергей", "Иванов", new Contact());
        Client updated = new Client(1L, "Сергей", "Петров", new Contact());

        when(clientRepository.findById(1L)).thenReturn(Optional.of(original));
        when(clientRepository.save(any(Client.class))).thenReturn(updated);

        Client result = clientService.update(updated);

        assertEquals("Петров", result.getLastName());
        verify(clientRepository).save(original);
    }

    @Test
    void update_nonExistingClient_shouldThrowException() {
        Client client = new Client(42L, "Нет", "Такого", new Contact());
        when(clientRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clientService.update(client));
    }

    @Test
    void deleteClientById_shouldCallDelete() {
        clientService.deleteClientById(1L);
        verify(clientRepository).deleteById(1L);
    }
}
