package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // On injecte le service qu'on a créé pour sauvegarder les fichiers
    @Autowired
    private FileUploadService fileUploadService;

    // --- NOUVELLE METHODE POUR CREER UN CLIENT AVEC PHOTO ---
    @PostMapping(consumes = {"multipart/form-data"})
    public Client saveClient(
            @RequestParam("name") String name,
            @RequestParam("contact") String contact,
            @RequestParam("email") String email,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("authorization") String authorization,
            @RequestParam(value = "passportPhotoFile", required = false) MultipartFile passportPhotoFile) throws IOException {

        Client client = new Client();
        client.setName(name);
        client.setContact(contact);
        client.setEmail(email);
        client.setAccountNumber(accountNumber);
        client.setAuthorization(authorization);

        // Si une image a été scannée, on la sauvegarde physiquement
        if (passportPhotoFile != null && !passportPhotoFile.isEmpty()) {
            String imageUrl = fileUploadService.saveImage(passportPhotoFile);
            client.setPassportPhoto(imageUrl); // On stocke le lien "/uploads/xxx.jpg"
        }

        return clientRepository.save(client);
    }

    // Get all clients from the database
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Search clients by name
    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String name) {
        return clientRepository.findByNameContainingIgnoreCase(name);
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client client = clientRepository.findById(id).orElseThrow();

        client.setName(clientDetails.getName());
        client.setContact(clientDetails.getContact());
        client.setEmail(clientDetails.getEmail());
        client.setAccountNumber(clientDetails.getAccountNumber());
        client.setPassportPhoto(clientDetails.getPassportPhoto());
        client.setAuthorization(clientDetails.getAuthorization());

        return clientRepository.save(client);
    }
    // Route publique pour créer un client depuis le site web
    @PostMapping("/public")
    public Client createPublicClient(@RequestBody Client client) {
        client.setAuthorization("Pending"); // En attente de validation par le staff
        return clientRepository.save(client);
    }
}