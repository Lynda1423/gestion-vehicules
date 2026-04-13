package fr.sgfv.conducteurs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignationResponseDto {
<<<<<<< HEAD
    private Long id;
    private ConducteurDto conducteur;
    private Long vehiculeId;
=======
    private UUID id;
    private ConducteurDto conducteur;
    private UUID vehiculeId;
>>>>>>> lynda
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
}
