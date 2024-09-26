package dk.lyngby.model;

import dk.lyngby.dto.HotelDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Rouvi
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private Set<Room> rooms;


    public Hotel(HotelDTO hotelDTO) {
        this.id = hotelDTO.getId();
        this.name = hotelDTO.getName();
        this.address = hotelDTO.getAddress();
        this.rooms = hotelDTO.getRooms();
    }

}
