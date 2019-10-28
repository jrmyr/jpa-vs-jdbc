package de.myrnet.jpavsjdbc.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Slf4j
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class DbBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    protected UUID id;

    protected final LocalDateTime created = LocalDateTime.now();

    protected LocalDateTime updated = created;

    /**
     * Method to set a newly created UUID manually.<br>
     * <b><i><u>NOTE:</u></i></b> Use only on special/exceptional cases as generally the ORM/DB is supposed to set it.
     * @return
     */
    public void setId() {
        if (id != null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " object has ID set already: " + id);
        }
        id = UUID.randomUUID();
    }

    @PrePersist
    @PreUpdate
    public void updateUpdatedAt() {
        if (updated.isEqual(created)) {
            // Dates are equal at creation time but we don't want to overwrite 'updated' with the date it is actually persisted
            return;
        }
        updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + id;
    }

}
