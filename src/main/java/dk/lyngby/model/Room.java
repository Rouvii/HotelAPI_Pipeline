package dk.lyngby.model;

import dk.lyngby.dto.RoomDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rouvi
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int number;
    private int price;
    private long hotelId;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;


    public Room(RoomDTO roomDTO) {
        this.id = roomDTO.getId();
        this.number = roomDTO.getNumber();
        this.price = roomDTO.getPrice();
        this.hotelId = roomDTO.getHotelId();

    }

}
