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
 * @author Rouvi
 */


public class RoomController {
    private final RoomDAO roomDAO;

    private final Logger log = LoggerFactory.getLogger(HotelController.class);


    public RoomController(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void getRoomById(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            if(room == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Room not found"));
                return;
            }

            RoomDTO roomDTO = new RoomDTO(room);
            ctx.res().setStatus(200);
            ctx.json(roomDTO, RoomDTO.class);
        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void getAllRooms(Context ctx) {
        try {
            List<Room> rooms = roomDAO.getAll();
            if (rooms.isEmpty()) {
                ctx.status(404);
                ctx.json(new Message(404, "No rooms found"));
                return;
            }
            List<RoomDTO> roomDTOList = new RoomDTO().toRoomDTOList(rooms);
            ctx.res().setStatus(200);
            ctx.json(roomDTOList, RoomDTO.class);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


    public void createRoom(Context ctx) {
        try {
            RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
            if(roomDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400,"Invalid input"));
                return;
            }
            Room newRoom = new Room(roomDTO);
            roomDAO.create(newRoom);
            ctx.res().setStatus(201);

        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void updateRoom(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
            if(room == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Room not found"));
                return;
            }

            if(roomDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400,"Invalid input"));
                return;
            }

            roomDAO.update(room,new Room(roomDTO));
            ctx.res().setStatus(204);

        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void deleteRoom(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Room room = roomDAO.getById(id);
            if(room == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Room not found"));
                return;
            }
            roomDAO.delete(id);
            ctx.res().setStatus(204);

        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


}
