package Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "update_demande_history")
public class UpdateDemandeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code_demande")
    private String codeDemande;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

