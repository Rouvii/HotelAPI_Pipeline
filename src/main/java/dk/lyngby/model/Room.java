package dk.lyngby.model;

import dk.lyngby.dto.RoomDTO;
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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private int price;

   @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Room(RoomDTO roomDTO) {
        this.id = roomDTO.getId();
        this.roomNumber = roomDTO.getRoomNumber();
        this.price = roomDTO.getPrice();

    }




}
