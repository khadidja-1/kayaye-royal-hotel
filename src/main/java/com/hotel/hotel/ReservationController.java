package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        Client client = clientRepository.findById(request.getClientId()).orElseThrow();
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow();

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setRoom(room);
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setStatus("Confirmed");
        reservation.setIsPaid(false);

        room.setStatus("Occupied");
        roomRepository.save(room);

        // On sauvegarde d'abord pour avoir l'ID
        Reservation savedReservation = reservationRepository.save(reservation);

        // On génère le lien
        String digitalKeyLink = "http://localhost:8081/digital-key.html?id=" + savedReservation.getId();

        try {
            emailService.sendConfirmationEmail(
                    client.getEmail(),
                    client.getName(),
                    room.getRoomNumber(),
                    request.getCheckInDate().toString(),
                    request.getCheckOutDate().toString(),
                    savedReservation.getId() // <--- C'EST LA LIGNE QUI MANQUAIT
            );
            System.out.println("✅ Email envoyé avec succès à " + client.getEmail());
        } catch (Exception e) {
            System.out.println("❌ Échec de l'envoi de l'email : " + e.getMessage());
            System.out.println("📧 SIMULATION : Email de confirmation pour " + client.getName() + " (Chambre " + room.getRoomNumber() + ")");
        }

        // --- AFFICHAGE DU LIEN DANS LA CONSOLE ---
        System.out.println("=====================================================");
        System.out.println("🔗 LIEN CLE DIGITALE POUR LE CLIENT :");
        System.out.println(digitalKeyLink);
        System.out.println("=====================================================");

        return savedReservation;
    }

    // --- MODIFICATION ICI : La chambre passe en "Cleaning" ---
    @PutMapping("/checkout/{id}")
    public Reservation checkout(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        Room room = reservation.getRoom();

        room.setStatus("Cleaning");
        roomRepository.save(room);

        reservation.setStatus("Checked-Out");
        return reservationRepository.save(reservation);
    }

    // --- ROUTE POUR LE PAIEMENT ---
    @PutMapping("/pay/{id}")
    public Reservation markAsPaid(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setIsPaid(true);
        return reservationRepository.save(reservation);
    }

    // Route pour le check-in en ligne
    @GetMapping("/digital-key/{id}")
    public Reservation getDigitalKey(@PathVariable Long id) {
        return reservationRepository.findById(id).orElseThrow();
    }
}