package Entities;
import Enum.*;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Simulation {
    private Employe employe;
    private Agence agence;
    private Client client;
    private Integer price;
    private Integer duration;
    private String remarque;
    private LocalDateTime date;
    private String number;
    private StatusDemande status;




    }
