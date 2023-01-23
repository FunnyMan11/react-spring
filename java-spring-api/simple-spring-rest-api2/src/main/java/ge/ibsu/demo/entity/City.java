package ge.ibsu.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="city")
public class City {
    @Id
    @SequenceGenerator(name = "city_city_id_seq", sequenceName = "city_city_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_city_id_seq")
    @Column(name="city_id")
    private Long city_id;
    @Column(name="city")
    private String city;

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
