package fr.hb.examen_dovene;

import fr.hb.examen_dovene.model.Client;
import fr.hb.examen_dovene.model.Reservation;
import fr.hb.examen_dovene.model.Table;
import fr.hb.examen_dovene.repository.ClientRepository;
import fr.hb.examen_dovene.repository.ReservationRepository;
import fr.hb.examen_dovene.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public DataInitializer(ClientRepository clientRepository, TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Création des tables
        Table table1 = new Table();
        table1.setNbrPlace(4);
        table1.setStatut("disponible");

        Table table2 = new Table();
        table2.setNbrPlace(6);
        table2.setStatut("en entretien");

        Table table3 = new Table();
        table3.setNbrPlace(2);
        table3.setStatut("reservé");

        tableRepository.saveAll(List.of(table1, table2, table3));

        // Création des clients
        Client client1 = new Client();
        client1.setNom("Dupont");
        client1.setPrenom("Jean");
        client1.setEmail("jean.dupont@example.com");
        client1.setTelephone("0123456789");
        client1.setGenre("Homme");
        client1.setVip(true);

        Client client2 = new Client();
        client2.setNom("Durand");
        client2.setPrenom("Marie");
        client2.setEmail("marie.durand@example.com");
        client2.setTelephone("0987654321");
        client2.setGenre("Femme");
        client2.setVip(false);

        clientRepository.saveAll(List.of(client1, client2));

        // Création des réservations
        Reservation reservation1 = new Reservation();
        reservation1.setTableReserve(table1);
        reservation1.setClient(client1);
        reservation1.setDate(LocalDateTime.now().plusDays(1));
        reservation1.setNbrCouvert(4);

        Reservation reservation2 = new Reservation();
        reservation2.setTableReserve(table2);
        reservation2.setClient(client2);
        reservation2.setDate(LocalDateTime.now().plusDays(2));
        reservation2.setNbrCouvert(6);

        reservationRepository.saveAll(List.of(reservation1, reservation2));

        System.out.println("Données de base initialisées !");
    }
}
