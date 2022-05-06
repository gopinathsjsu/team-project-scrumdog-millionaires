const model = require("./../models/bookingsModel");
const roomModel = require("./../models/roomsModel");
const PricingService = require("./pricing_service");
const {
  HTTP_404,
  HTTP_500,
  HTTP_RES,
  IS_VALID_DATE,
} = require("./../Utilities/http_utils");

class BookingService {
  constructor() {
    this.FETCHED_BOOKINGS = "Fetched Bookings";
    this.AMMENITIES = {
      breakfast: "Breakfast",
      fitness_room: "Fitness Room",
      parking: "Parking",
      pool: "Pool",
      all_meals: "All meals",
    };

    this.ROOM_TYPES = {
      single: "Single",
      double: "Double",
      suite: "Suite",
    };
  }

  async getHotelBookings() {
    try {
      const bookings = await model.getHotelBookings();
      return {
        status: 200,
        data: bookings,
        msg: this.FETCHED_BOOKINGS,
      };
    } catch (err) {
      console.error(
        "BookingService::getHotelBookings::Uncaught exception\n",
        err
      );
      return HTTP_500();
    }
  }
}
