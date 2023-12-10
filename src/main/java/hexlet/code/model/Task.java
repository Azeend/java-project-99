package hexlet.code.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 1)
    private String name;

    private int index;
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    @CreatedDate
    private LocalDate createdAt;
    @ManyToMany
    private Set<Label> labels;
}
