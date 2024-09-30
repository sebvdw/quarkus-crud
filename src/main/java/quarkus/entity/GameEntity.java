package quarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class GameEntity extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    public GameEntity() {}

    public GameEntity(String name) {
        this.name = name;
    }
}
