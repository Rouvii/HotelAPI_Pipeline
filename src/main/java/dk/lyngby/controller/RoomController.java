package dk.lyngby.controller;

import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dao.RoomDAO;
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
public class RoomController {

    private final Logger log = LoggerFactory.getLogger(RoomController.class);

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;

    public RoomController(RoomDAO roomDAO, HotelDAO hotelDAO) {
        this.roomDAO = roomDAO;
        this.hotelDAO = hotelDAO;
    }

    public void createRoom(Context ctx){
       try{
           RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
           if (roomDTO == null) {
               ctx.status(400);
               ctx.json(new Message(400,"Invalid Request"));
               return;
           }
           log.debug("Recevied RoomDTO: {}",roomDTO);


           Hotel hotel = hotelDAO.getById(roomDTO.getHotel().getId());
              if (hotel == null) {
                ctx.status(400);
                ctx.json(new Message(400,"Hotel not found"));
                return;
              }

              log.debug("Retrived Hotel: {}",hotel);


           Room newRoom = new Room(roomDTO);
           newRoom.setHotel(hotel);
           log.debug("New room with hotel set: {}",newRoom);
              roomDAO.create(newRoom);
              ctx.status(201).json(new RoomDTO(newRoom));
              //ctx.res().setStatus(201);
       }catch (Exception e){
           log.error("400 {}",e.getMessage());
           throw new ApiException(400,e.getMessage());
       }

    }

    public void getRoomById(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        Room room = roomDAO.getById(id);
        RoomDTO roomDTO = new RoomDTO(room);
        ctx.res().setStatus(200);
        ctx.json(roomDTO,RoomDTO.class);
    }

    public void getAllRooms(Context ctx){
        List<Room> rooms = roomDAO.getAll();
        List<RoomDTO> roomDTOs = RoomDTO.toRoomDTOList(rooms);
        ctx.res().setStatus(200);
        ctx.json(roomDTOs,RoomDTO.class);
    }

    public void updateRoom(Context ctx){
        long id = Long .parseLong(ctx.pathParam("id"));
        RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);

        Room room = roomDAO.getById(id);
        roomDAO.update(room,new Room(roomDTO));
        ctx.res().setStatus(200);
    }

    public void deleteRoom(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        Room room = roomDAO.getById(id);
        roomDAO.delete(id);
        ctx.res().setStatus(204);
    }



}
