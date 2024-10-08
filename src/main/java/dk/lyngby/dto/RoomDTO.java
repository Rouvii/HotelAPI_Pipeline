package dk.lyngby.dto;

import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author Rouvi
 */

@NoArgsConstructor
@Data
public class RoomDTO {
    private Long id;
    private Hotel hotel;
    private int roomNumber;
    private int price;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.hotel = room.getHotel();
        this.roomNumber = room.getRoomNumber();
        this.price = room.getPrice();
    }

    public static List<RoomDTO> toRoomDTOList(List<Room> rooms) {
        return rooms.stream().map(RoomDTO::new).toList();
    }
}
