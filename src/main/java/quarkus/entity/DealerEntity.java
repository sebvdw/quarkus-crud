package quarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class DealerEntity extends PanacheEntity {

    @Column(length = 40)
    public String name;

    @Column(length = 20)
    public String employeeId;

    public DealerEntity() {}

    public DealerEntity(String name, String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }
}
