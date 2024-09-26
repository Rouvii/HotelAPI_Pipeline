package dk.lyngby.dto;

import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Rouvi
 */

@Data
@NoArgsConstructor
public class HotelDTO {
    private long id;
    private String name;
    private String address;
    private Set<Room> rooms;


    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.rooms = hotel.getRooms();
    }

 public List<HotelDTO> toHotelDTOList(List<Hotel> hotels) {
        return hotels.stream().map(HotelDTO::new).toList();
    }



}
