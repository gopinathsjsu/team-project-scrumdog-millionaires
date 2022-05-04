const { update } = require("../models/bookingsModel");
const model = require("./../models/hotelModel");
const { HTTP_500, HTTP_RES } = require("./../Utilities/http_utils");

class HotelService {
  async getHotels() {
    try {
      const hotels = await model.getHotels();
      return {
        status: 200,
        data: hotels,
        msg: "Fetched hotels",
      };
    } catch (err) {
      console.error("HotelService::getHotels::Uncaught exception\n", err);
      return HTTP_500();
    }
  }

  async getHotelsByID(hotel_id) {
    try {
      const hotel = await model.getHotelsByID(hotel_id);
      if (!Array.isArray(hotel) || hotel.length == 0)
        return HTTP_RES(404, "No hotel found");
      return HTTP_RES(200, "Fetched hotel", hotel);
    } catch (err) {
      console.error(
        `HotelService::getHotelsByID/${hotel_id}::Uncaught exception\n, ${err}`
      );
      return HTTP_500();
    }
  }
}
