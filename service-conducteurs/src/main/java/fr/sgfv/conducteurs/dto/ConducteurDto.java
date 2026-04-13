package fr.sgfv.conducteurs.dto;

import fr.sgfv.conducteurs.entity.ConducteurStatut;
import fr.sgfv.conducteurs.entity.Disponibilite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> lynda

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConducteurDto {
<<<<<<< HEAD
    private Long id;
    private String nom;
    private String prenom;
    private String numeroPermis;
    private String typePermis;
    private LocalDate dateExpirationPermis;
    private ConducteurStatut statut;
=======
    private UUID id;
    private String keycloakId;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numeroPermis;
    private LocalDate dateExpirationPermis;
    private UUID vehiculeAssigneId;
    private ConducteurStatut statutCompte;
    private Disponibilite disponibilite;
>>>>>>> lynda
}
