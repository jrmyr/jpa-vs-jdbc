package de.myrnet.jpavsjdbc.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
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

    protected LocalDateTime updated = LocalDateTime.now();

    @PrePersist
    @PreUpdate
    public void updateUpdatedAt() {
        updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + id;
    }

}
