package com.mtuomiko.springtodobackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {
    @CreationTimestamp
    private LocalDateTime creation;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OneToMany(mappedBy = "account")
    private List<Todo> todos = new ArrayList<>();
}
