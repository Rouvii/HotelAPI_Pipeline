package dk.lyngby.controller;

import dk.lyngby.dao.HotelDAO;
import dk.lyngby.dto.HotelDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Message;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Rouvi
 */


public class HotelController {

    private final HotelDAO hotelDAO;

    private final Logger log = LoggerFactory.getLogger(HotelController.class);

    public HotelController(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


    public void getHotelById(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            if(hotel == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Hotel not found"));
                return;
            }

            HotelDTO hotelDTO = new HotelDTO(hotel);
            ctx.res().setStatus(200);
            ctx.json(hotelDTO, HotelDTO.class);
        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void getAllHotels(Context ctx) {
        try {
            List<Hotel> hotels = hotelDAO.getAll();
            if(hotels.isEmpty()) {
                ctx.status(404);
                ctx.json(new Message(404, "No hotels found"));
                return;
            }
            List<HotelDTO> hotelDTOList = new HotelDTO().toHotelDTOList(hotels);
            ctx.res().setStatus(200);
            ctx.json(hotelDTOList, HotelDTO.class);


        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void createHotel(Context ctx) {
       try {
           HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
           if(hotelDTO == null) {
               ctx.status(400);
               ctx.json(new Message(400,"Invalid input"));
               return;
           }
           Hotel newHotel = new Hotel(hotelDTO);
              hotelDAO.create(newHotel);
              ctx.res().setStatus(201);

       }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


    public void updateHotel(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
            if(hotelDTO == null) {
                ctx.status(400);
                ctx.json(new Message(400,"Invalid input"));
                return;
            }
            Hotel hotel = hotelDAO.getById(id);
            if(hotel == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Hotel not found"));
                return;
            }

            hotelDAO.update(hotel, new Hotel(hotelDTO));
            ctx.res().setStatus(204);

        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    public void deleteHotel(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(id);
            if(hotel == null) {
                ctx.status(404);
                ctx.json(new Message(404, "Hotel not found"));
                return;
            }
            hotelDAO.delete(id);
            ctx.res().setStatus(204);

        }catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }





}
