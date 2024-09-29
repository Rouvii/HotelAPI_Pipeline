package dk.lyngby.model;

import dk.lyngby.dto.HotelDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

/**
 * @author Rouvi
 */


@NoArgsConstructor
@Data
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;
    private String name;
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Room> rooms;


    public Hotel(HotelDTO hotelDTO) {
        this.id = hotelDTO.getId();
        this.name = hotelDTO.getName();
        this.address = hotelDTO.getAddress();
    }


}
