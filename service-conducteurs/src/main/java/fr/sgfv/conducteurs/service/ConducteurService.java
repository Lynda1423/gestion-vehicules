package fr.sgfv.conducteurs.service;

import fr.sgfv.conducteurs.entity.Conducteur;
import fr.sgfv.conducteurs.dto.ConducteurDto;
import fr.sgfv.conducteurs.kafka.ConducteurKafkaProducer;
import fr.sgfv.conducteurs.repository.ConducteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConducteurService {

    private final ConducteurRepository conducteurRepository;
    private final ConducteurKafkaProducer kafkaPublisher;

    @Transactional(readOnly = true)
    public List<ConducteurDto> getAllConducteurs() {
        return conducteurRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
<<<<<<< HEAD
    public ConducteurDto getConducteurById(Long id) {
=======
    public ConducteurDto getConducteurById(UUID id) {
>>>>>>> lynda
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur introuvable"));
        return mapToDto(conducteur);
    }

    @Transactional
    public ConducteurDto createConducteur(ConducteurDto dto) {
        if (conducteurRepository.findByNumeroPermis(dto.getNumeroPermis()).isPresent()) {
            throw new RuntimeException("Un conducteur avec ce permis existe déjà");
        }

        Conducteur conducteur = Conducteur.builder()
<<<<<<< HEAD
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .numeroPermis(dto.getNumeroPermis())
                .typePermis(dto.getTypePermis())
                .dateExpirationPermis(dto.getDateExpirationPermis())
                .statut(dto.getStatut() != null ? dto.getStatut() : fr.sgfv.conducteurs.entity.ConducteurStatut.ACTIF)
=======
                .keycloakId(dto.getKeycloakId() != null ? dto.getKeycloakId() : UUID.randomUUID().toString()) // default mock if not provided
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail() != null ? dto.getEmail() : "default@example.com")
                .telephone(dto.getTelephone())
                .numeroPermis(dto.getNumeroPermis())
                .dateExpirationPermis(dto.getDateExpirationPermis())
                .statutCompte(dto.getStatutCompte() != null ? dto.getStatutCompte() : fr.sgfv.conducteurs.entity.ConducteurStatut.ACTIF)
                .disponibilite(fr.sgfv.conducteurs.entity.Disponibilite.DISPONIBLE)
>>>>>>> lynda
                .build();

        Conducteur savedConducteur = conducteurRepository.save(conducteur);
        
        kafkaPublisher.publishConducteurCreated(savedConducteur.getId());
        
        return mapToDto(savedConducteur);
    }

<<<<<<< HEAD
    private ConducteurDto mapToDto(Conducteur conducteur) {
        return ConducteurDto.builder()
                .id(conducteur.getId())
                .nom(conducteur.getNom())
                .prenom(conducteur.getPrenom())
                .numeroPermis(conducteur.getNumeroPermis())
                .typePermis(conducteur.getTypePermis())
                .dateExpirationPermis(conducteur.getDateExpirationPermis())
                .statut(conducteur.getStatut())
=======
    @Transactional
    public ConducteurDto updateConducteur(UUID id, ConducteurDto dto) {
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur introuvable"));

        if (dto.getNom() != null) conducteur.setNom(dto.getNom());
        if (dto.getPrenom() != null) conducteur.setPrenom(dto.getPrenom());
        if (dto.getEmail() != null) conducteur.setEmail(dto.getEmail());
        if (dto.getTelephone() != null) conducteur.setTelephone(dto.getTelephone());
        if (dto.getNumeroPermis() != null) conducteur.setNumeroPermis(dto.getNumeroPermis());
        if (dto.getDateExpirationPermis() != null) conducteur.setDateExpirationPermis(dto.getDateExpirationPermis());

        Conducteur saved = conducteurRepository.save(conducteur);
        // kafkaPublisher.publishConducteurUpdated(saved.getId()); // TODO: Implement if needed
        return mapToDto(saved);
    }

    @Transactional
    public ConducteurDto changeStatut(UUID id, fr.sgfv.conducteurs.entity.ConducteurStatut statut) {
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur introuvable"));

        conducteur.setStatutCompte(statut);
        Conducteur saved = conducteurRepository.save(conducteur);
        // kafkaPublisher.publishConducteurStatusChanged(saved.getId(), statut.name()); // TODO: Implement if needed
        return mapToDto(saved);
    }

    @Transactional
    public ConducteurDto changeDisponibilite(UUID id, fr.sgfv.conducteurs.entity.Disponibilite disponibilite) {
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur introuvable"));

        conducteur.setDisponibilite(disponibilite);
        Conducteur saved = conducteurRepository.save(conducteur);
        // kafkaPublisher.publishConducteurAvailabilityChanged(saved.getId(), disponibilite.name()); // TODO: Implement if needed
        return mapToDto(saved);
    }

    @Transactional
    public ConducteurDto deactivateConducteur(UUID id) {
        Conducteur conducteur = conducteurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conducteur introuvable"));

        conducteur.setStatutCompte(fr.sgfv.conducteurs.entity.ConducteurStatut.SUSPENDU);
        Conducteur saved = conducteurRepository.save(conducteur);
        // kafkaPublisher.publishConducteurDeactivated(saved.getId()); // TODO: Implement if needed
        return mapToDto(saved);
    }

    private ConducteurDto mapToDto(Conducteur conducteur) {
        return ConducteurDto.builder()
                .id(conducteur.getId())
                .keycloakId(conducteur.getKeycloakId())
                .nom(conducteur.getNom())
                .prenom(conducteur.getPrenom())
                .email(conducteur.getEmail())
                .telephone(conducteur.getTelephone())
                .numeroPermis(conducteur.getNumeroPermis())
                .dateExpirationPermis(conducteur.getDateExpirationPermis())
                .vehiculeAssigneId(conducteur.getVehiculeAssigneId())
                .statutCompte(conducteur.getStatutCompte())
                .disponibilite(conducteur.getDisponibilite())
>>>>>>> lynda
                .build();
    }
}
