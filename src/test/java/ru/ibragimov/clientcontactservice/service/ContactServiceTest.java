package ru.ibragimov.clientcontactservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ibragimov.clientcontactservice.entity.Contact;
import ru.ibragimov.clientcontactservice.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    private ContactRepository contactRepository;
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactRepository = mock(ContactRepository.class);
        contactService = new ContactService(contactRepository);
    }

    @Test
    void createContactReturnSavedContact() {
        Contact contact = new Contact(null, "123456", "test@mail.com");
        Contact saved = new Contact(1L, "123456", "test@mail.com");

        when(contactRepository.save(contact)).thenReturn(saved);

        Contact result = contactService.createContact(contact);

        assertEquals(saved, result);
        verify(contactRepository).save(contact);
    }

    @Test
    void createContactThrowException() {
        assertThrows(IllegalArgumentException.class, () -> contactService.createContact(null));
        verify(contactRepository, never()).save(any());
    }

    @Test
    void getAllContactsReturnAll() {
        List<Contact> list = List.of(new Contact(1L, "123", "a@a.com"));
        when(contactRepository.findAll()).thenReturn(list);

        List<Contact> result = contactService.getAllContacts();

        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getPhone());
    }

    @Test
    void getContactByIdReturnContact() {
        Contact contact = new Contact(1L, "123", "a@a.com");
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        Optional<Contact> result = contactService.getContactById(1L);

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getPhone());
    }

    @Test
    void getContactByIdReturnEmpty() {
        when(contactRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Contact> result = contactService.getContactById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void updateContactUpdateAndReturn() {
        Contact existing = new Contact(1L, "111", "old@mail.com");
        Contact update = new Contact(1L, "999", "new@mail.com");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(contactRepository.save(any())).thenReturn(update);

        Contact result = contactService.updateContact(update);

        assertEquals("999", result.getPhone());
        assertEquals("new@mail.com", result.getEmail());
    }

    @Test
    void updateContactThrow() {
        Contact update = new Contact(42L, "?", "?");

        when(contactRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> contactService.updateContact(update));
    }

    @Test
    void deleteContactRepo() {
        contactService.deleteContactById(1L);
        verify(contactRepository).deleteById(1L);
    }
}
