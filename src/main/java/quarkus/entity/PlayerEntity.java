package quarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@Cacheable
public class PlayerEntity extends PanacheEntity {

    @Column(length = 40)
    public String name;

    @ManyToOne
    public GameEntity game;

    public PlayerEntity() {}

    public PlayerEntity(String name, GameEntity game) {
        this.name = name;
        this.game = game;
    }
}
