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
  /**
   * @description GET /hotels
   * consumed by both users and hotels
   */

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
}
