package com.example.demo;

import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import com.example.demo.ws.CompteSoapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SoapServiceTest {

    @Autowired
    private CompteSoapService compteSoapService;

    @Test
    void testGetComptes() {
        List<Compte> comptes = compteSoapService.getComptes();
        assertNotNull(comptes);
        assertFalse(comptes.isEmpty());
    }

    @Test
    void testCreateAndGetCompte() {
        Compte created = compteSoapService.createCompte(5500.0, TypeCompte.COURANT);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(5500.0, created.getSolde());

        Compte found = compteSoapService.getCompteById(created.getId());
        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void testDeleteCompte() {
        Compte created = compteSoapService.createCompte(3000.0, TypeCompte.EPARGNE);
        Long id = created.getId();
        boolean deleted = compteSoapService.deleteCompte(id);
        assertTrue(deleted);

        Compte notFound = compteSoapService.getCompteById(id);
        assertNull(notFound);
    }
}
