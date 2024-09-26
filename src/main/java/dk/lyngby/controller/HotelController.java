package dk.lyngby.controller;

import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
import dk.lyngby.dto.HotelDTO;
import dk.lyngby.dto.RoomDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Message;
import dk.lyngby.model.Room;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Kevin Løvstad Schou
 */
public class HotelController {
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;
    private final Logger log = LoggerFactory.getLogger(HotelController.class);

    public HotelController(HotelDAO hotelDAO, RoomDAO roomDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
    }


//    public void createHotel(Context ctx){
//        try{
//            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
//
//            if (hotelDTO == null) {
//                ctx.status(400);
//                ctx.json(new Message(400,"Invalid Request"));
//                return;
//            }
//            Hotel newHotel = new Hotel(hotelDTO);
//            hotelDAO.create(newHotel);
//            //TESTING
//           if (hotelDTO.getRooms() != null){
//               for (RoomDTO roomDTO : hotelDTO.getRooms()){
//                   Room room = new Room(roomDTO);
//                   room.setHotel(newHotel);
//                   roomDAO.create(room);{
//               }
//           }
//            //TESTING
//            ctx.res().setStatus(201);
//        }catch (Exception e){
//            log.error("400 {}", e.getMessage());
//            throw new ApiException(400, e.getMessage());
//
//        }
//
//    }


    public void createHotel(Context ctx) {
        try {
            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);

            if (hotelDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400, "Invalid Request"));
                return;
            }

            Hotel newHotel = new Hotel(hotelDTO);
            hotelDAO.create(newHotel);

            if (hotelDTO.getRooms() != null) {
                for (RoomDTO roomDTO : hotelDTO.getRooms()) {
                    Room newRoom = new Room(roomDTO);
                    newRoom.setHotel(newHotel);
                    roomDAO.create(newRoom);
                }
            }

            ctx.res().setStatus(201);
        } catch (Exception e) {
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void getAllHotels(Context ctx){
   List<Hotel> hotelList = hotelDAO.getAll();
   List<HotelDTO> hotelDTOList = HotelDTO.toHotelDTOList(hotelList);
   ctx.res().setStatus(200);
    ctx.json(hotelDTOList,HotelDTO.class);

    }

    public void updateHotel(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);

        Hotel hotel = hotelDAO.getById(id);
        if(hotel == null){
            ctx.status(404);
            ctx.json(new Message(404,"Hotel not found"));
            return;
        }
        ctx.res().setStatus(200);
    }



    public void getHotelById(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));

        Hotel hotel = hotelDAO.getById(id);

        HotelDTO hotelDTO = new HotelDTO(hotel);
        ctx.res().setStatus(200);
        ctx.json(hotelDTO,HotelDTO.class);
    }

    public void deleteHotel(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));

        Hotel hotel = hotelDAO.getById(id);

        if(hotel == null){
            ctx.status(404);
            ctx.json(new Message(404,"Hotel not found"));
            return;
        }
        hotelDAO.delete(id);
        ctx.res().setStatus(204);
    }
}
