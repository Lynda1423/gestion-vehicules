<<<<<<< HEAD
package fr.sgfv.vehicules.service;

=======
package fr.sgfv.vehicules;
import fr.sgfv.vehicules.service.VehiculeService;
>>>>>>> bc6798a (modification de service vehicules)
import fr.sgfv.vehicules.dto.*;
import fr.sgfv.vehicules.entity.Vehicule;
import fr.sgfv.vehicules.exception.*;
import fr.sgfv.vehicules.kafka.VehiculeKafkaProducer;
import fr.sgfv.vehicules.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Mock
    private VehiculeKafkaProducer kafkaProducer;

    @InjectMocks
    private VehiculeService vehiculeService;

    private Vehicule vehicule;
    private UUID vehiculeId;

    @BeforeEach
    void setUp() {
        vehiculeId = UUID.randomUUID();
        vehicule = Vehicule.builder()
<<<<<<< HEAD
            .id(vehiculeId)
            .plaque("AB-123-CD")
            .marque("Renault")
            .modele("Clio")
            .annee(2021)
            .kilometrage(15000)
            .statut("DISPONIBLE")
            .build();
=======
                .id(vehiculeId)
                .plaque("AB-123-CD")
                .marque("Renault")
                .modele("Clio")
                .annee(2021)
                .kilometrage(15000)
                .statut("DISPONIBLE")
                .build();
>>>>>>> bc6798a (modification de service vehicules)
    }

    // ── Tests creerVehicule ───────────────────────

    @Test
    void creerVehicule_succes() {
        CreateVehiculeDTO dto = new CreateVehiculeDTO();
        dto.setPlaque("AB-123-CD");
        dto.setMarque("Renault");
        dto.setModele("Clio");
        dto.setAnnee(2021);
        dto.setKilometrage(0);

        when(vehiculeRepository.existsByPlaque("AB-123-CD")).thenReturn(false);
        when(vehiculeRepository.save(any())).thenReturn(vehicule);

        VehiculeDTO result = vehiculeService.creerVehicule(dto);

        assertThat(result).isNotNull();
        assertThat(result.getPlaque()).isEqualTo("AB-123-CD");
        verify(kafkaProducer).envoyerVehiculeCreated(any());
    }

    @Test
    void creerVehicule_plaqueExistante_lanceException() {
        CreateVehiculeDTO dto = new CreateVehiculeDTO();
        dto.setPlaque("AB-123-CD");
        dto.setMarque("Renault");
        dto.setModele("Clio");
        dto.setAnnee(2021);

        when(vehiculeRepository.existsByPlaque("AB-123-CD")).thenReturn(true);

        assertThatThrownBy(() -> vehiculeService.creerVehicule(dto))
<<<<<<< HEAD
            .isInstanceOf(PlaqueDejaExistanteException.class);
=======
                .isInstanceOf(PlaqueDejaExistanteException.class);
>>>>>>> bc6798a (modification de service vehicules)

        verify(vehiculeRepository, never()).save(any());
    }

    // ── Tests getVehicule ─────────────────────────

    @Test
    void getVehicule_succes() {
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        VehiculeDTO result = vehiculeService.getVehicule(vehiculeId);

        assertThat(result.getId()).isEqualTo(vehiculeId);
        assertThat(result.getStatut()).isEqualTo("DISPONIBLE");
    }

    @Test
    void getVehicule_introuvable_lanceException() {
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculeService.getVehicule(vehiculeId))
            .isInstanceOf(VehiculeNotFoundException.class);
=======
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculeService.getVehicule(vehiculeId))
                .isInstanceOf(VehiculeNotFoundException.class);
>>>>>>> bc6798a (modification de service vehicules)
    }

    // ── Tests modifierVehicule ────────────────────

    @Test
    void modifierVehicule_horsService_lanceException() {
        vehicule.setStatut("HORS_SERVICE");
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        UpdateVehiculeDTO dto = new UpdateVehiculeDTO();
        dto.setMarque("Peugeot");

        assertThatThrownBy(() -> vehiculeService.modifierVehicule(vehiculeId, dto))
<<<<<<< HEAD
            .isInstanceOf(VehiculeHorsServiceException.class);
=======
                .isInstanceOf(VehiculeHorsServiceException.class);
>>>>>>> bc6798a (modification de service vehicules)
    }

    @Test
    void modifierVehicule_kilometrageDecroissant_lanceException() {
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        UpdateVehiculeDTO dto = new UpdateVehiculeDTO();
        dto.setKilometrage(1000); // inférieur à 15000

        assertThatThrownBy(() -> vehiculeService.modifierVehicule(vehiculeId, dto))
<<<<<<< HEAD
            .isInstanceOf(KilometrageInvalideException.class);
=======
                .isInstanceOf(KilometrageInvalideException.class);
>>>>>>> bc6798a (modification de service vehicules)
    }

    // ── Tests changerStatut ───────────────────────

    @Test
    void changerStatut_transitionValide_succes() {
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)
        when(vehiculeRepository.save(any())).thenReturn(vehicule);

        ChangerStatutDTO dto = new ChangerStatutDTO();
        dto.setStatut("EN_MISSION");

        VehiculeDTO result = vehiculeService.changerStatut(vehiculeId, dto);

        assertThat(result).isNotNull();
        verify(kafkaProducer).envoyerStatutChange(any(), eq("DISPONIBLE"));
    }

    @Test
    void changerStatut_transitionInvalide_lanceException() {
        vehicule.setStatut("HORS_SERVICE");
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        ChangerStatutDTO dto = new ChangerStatutDTO();
        dto.setStatut("EN_MISSION");

        assertThatThrownBy(() -> vehiculeService.changerStatut(vehiculeId, dto))
<<<<<<< HEAD
            .isInstanceOf(TransitionStatutInvalideException.class);
=======
                .isInstanceOf(TransitionStatutInvalideException.class);
>>>>>>> bc6798a (modification de service vehicules)
    }

    // ── Tests assignerConducteur ──────────────────

    @Test
    void assignerConducteur_vehiculeDisponible_succes() {
        UUID conducteurId = UUID.randomUUID();
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)
        when(vehiculeRepository.save(any())).thenReturn(vehicule);

        AssignerVehiculeDTO dto = new AssignerVehiculeDTO();
        dto.setConducteurId(conducteurId);

        VehiculeDTO result = vehiculeService.assignerConducteur(vehiculeId, dto);

        assertThat(result).isNotNull();
        verify(kafkaProducer).envoyerVehiculeAssigned(any());
    }

    @Test
    void assignerConducteur_vehiculeNonDisponible_lanceException() {
        vehicule.setStatut("EN_MISSION");
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        AssignerVehiculeDTO dto = new AssignerVehiculeDTO();
        dto.setConducteurId(UUID.randomUUID());

        assertThatThrownBy(() -> vehiculeService.assignerConducteur(vehiculeId, dto))
<<<<<<< HEAD
            .isInstanceOf(IllegalStateException.class);
=======
                .isInstanceOf(IllegalStateException.class);
>>>>>>> bc6798a (modification de service vehicules)
    }

    // ── Tests archiverVehicule ────────────────────

    @Test
    void archiverVehicule_succes() {
        when(vehiculeRepository.findById(vehiculeId))
<<<<<<< HEAD
            .thenReturn(Optional.of(vehicule));
=======
                .thenReturn(Optional.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)
        when(vehiculeRepository.save(any())).thenReturn(vehicule);

        VehiculeDTO result = vehiculeService.archiverVehicule(vehiculeId);

        assertThat(result).isNotNull();
        verify(kafkaProducer).envoyerVehiculeArchived(any());
    }

    // ── Tests listerVehicules ─────────────────────

    @Test
    void listerVehicules_sansFiltre_retourneTout() {
        when(vehiculeRepository.findAll()).thenReturn(List.of(vehicule));

        List<VehiculeDTO> result = vehiculeService.listerVehicules(null, null);

        assertThat(result).hasSize(1);
    }

    @Test
    void listerVehicules_avecStatut_filtrePaStatut() {
        when(vehiculeRepository.findByStatut("DISPONIBLE"))
<<<<<<< HEAD
            .thenReturn(List.of(vehicule));
=======
                .thenReturn(List.of(vehicule));
>>>>>>> bc6798a (modification de service vehicules)

        List<VehiculeDTO> result = vehiculeService.listerVehicules("DISPONIBLE", null);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatut()).isEqualTo("DISPONIBLE");
    }
}