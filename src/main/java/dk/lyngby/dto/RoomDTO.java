package dk.lyngby.dto;

import dk.lyngby.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RoomDTO class
 */
@Data
@NoArgsConstructor
public class RoomDTO {
    private long id;
    private long hotelId;
    private int number;
    private int price;

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.hotelId = room.getHotel().getId();
        this.number = room.getNumber();
        this.price = room.getPrice();
    }

    public List<RoomDTO> toRoomDTOList(List<Room> rooms) {
        return rooms.stream().map(RoomDTO::new).toList();
    }
}