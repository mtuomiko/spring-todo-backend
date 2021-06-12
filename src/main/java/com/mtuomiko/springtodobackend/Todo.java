package com.mtuomiko.springtodobackend;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends AbstractPersistable<Long> {
    @CreationTimestamp
    private LocalDateTime creationTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @ManyToOne
    @JsonIgnore
    private Account account;

    @NotBlank
    private String content;
    @NotNull
    private Boolean done;
}
