package Entities;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "update_demande_history")
public class UpdateDemandeHistory {
    @Id
    private int id;
    @Column(name = "code_demande")
    private String codeDemande;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
