package fr.sgfv.maintenance.graphql;

public record CloturerMaintenanceInput(
    String compteRendu,
    Float cout
) {}