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

  async getUserBookings(userId) {
    try {
      const bookings = await model.getUserBookings(userId);
      return {
        status: 200,
        data: bookings,
        msg: this.FETCHED_BOOKINGS,
      };
    } catch (err) {
      console.error(
        "BookingService::getUserBookings::Uncaught exception\n",
        err
      );
      return HTTP_500();
    }
  }

  async bookRoomForUser(roomId, bookingInfo) {
    try {
      // TODO: Validate booking information
      const { userId, start, end, total_guests, ammenities } = bookingInfo;

      if (!IS_VALID_DATE(start) && !IS_VALID_DATE(end)) {
        return HTTP_RES(400, "Invalid date format");
      }

      if (
        new Date(start) < new Date() ||
        new Date(end) < new Date() ||
        new Date(end) < new Date(start)
      ) {
        return HTTP_RES(400, "Invalid start and end dates");
      }
      // Assert room can be booked
      const existingBooking = await model.getBookingsByDates(
        new Date(start),
        new Date(end),
        roomId
      );
      if (Array.isArray(existingBooking) && existingBooking.length > 0) {
        return HTTP_RES(400, "Room Booking Exists");
      }

      // (a) Validate room exists (b) and is not booked
      const rooms = await roomModel.getRoomsByRoomID(roomId);
      if (!Array.isArray(rooms) || rooms.length == 0)
        return HTTP_404("No such room");

      const [roomObject] = rooms;
      const {
        min_guests,
        guest_fee,
        week_end_surge,
        festival_surge,
        base_price,
      } = roomObject;

      // Calculate price for the room
      const finalPrice = PricingService.calculateRoomPrice({
        base_fare: PricingService.get_base_fare(base_price, start, end),
        guest_charge: PricingService.guest_charge({
          total_guests,
          min_guests,
          guest_fee,
        }),
        surge_charge: PricingService.surge_charge({
          start,
          end,
          week_end_surge,
          festival_surge,
        }),
        customer_rewards: await PricingService.customer_rewards_static(userId),
      });

      const startDate = new Date(start);
      const endDate = new Date(end);

      await model.create(
        userId,
        roomId,
        finalPrice,
        startDate,
        endDate,
        total_guests,
        "confirmed"
      );

      const newlyCreatedBooking = await model.getBookingsByDates(
        startDate,
        endDate,
        roomId
      );
      return HTTP_RES(200, "Success", newlyCreatedBooking);
    } catch (e) {
      console.error("BookingService::bookRoomForUser::Uncaught exception\n", e);
      return HTTP_500();
    }
  }
}
