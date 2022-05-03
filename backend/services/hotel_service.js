const { update } = require("../models/bookingsModel");
const model = require("./../models/hotelModel");
const { HTTP_500, HTTP_RES } = require("./../Utilities/http_utils");

class HotelService {
  /**
   * @description GET /hotels
   * consumed by both users and hotels
   */
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
}
