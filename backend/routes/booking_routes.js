const express = require("express");
const SSecurity = require("../services/SimpleSecurity");
const router = express.Router();
const BookingService = require("./../services/booking_services");
const booking_service = new BookingService();

router.get("/users/:userId", async (request, response) => {
  const { params } = request;
  const { userId } = params;
  try {
    const { status, ...data } = await booking_service.getUserBookings(userId);
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::GET /bookings/users/${userId}:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.post("/rooms/:room_id", async (request, response) => {
  const { params, body } = request;
  const { room_id } = params;
  console.log(body);
  try {
    const { status, ...data } = await booking_service.bookRoomForUser(
      room_id,
      body
    );
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::POST /bookings/hotels/${hotel_id}/rooms/${room_id}:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.put("/:booking_id", async (request, response) => {
  const { query, params, body } = request;
  const { userId } = query;
  const { booking_id } = params;
  try {
    const { status, ...data } = await booking_service.updateBooking(
      userId,
      booking_id,
      body
    );
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::PUT /bookings/${booking_id}:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.delete("/:booking_id", async (request, response) => {
  const { query, params } = request;
  const { userId } = query;
  const { booking_id } = params;
  try {
    const { status, ...data } = await booking_service.cancelBooking(
      booking_id,
      userId
    );
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::DELETE /bookings/${booking_id}:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.get("/ammenities", async (request, response) => {
  try {
    const { status, ...data } = await booking_service.getAmmenities();
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::GET /ammenities:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.get("/room-types", async (request, response) => {
  try {
    const { status, ...data } = await booking_service.getRoomTypes();
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::GET /room-types:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.get("/get-estimate", async (request, response) => {
  try {
    const { query } = request;
    const { start, end, roomId, numberOfGuests, userId } = query;
    const { status, ...data } = await booking_service.estimatePrice(
      roomId,
      start,
      end,
      numberOfGuests,
      userId
    );
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::GET /room-types:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

router.get("/admin", async (request, response) => {
  const { params } = request;
  const { hotelId } = params;
  try {
    const { status, ...data } = await booking_service.getHotelBookings(hotelId);
    return response.status(status).send({ ...data });
  } catch (err) {
    console.error(
      `BookingRoutes::GET /bookings/hotels/${hotelId}:: Internal server error \n ${err}`
    );
    return response.status(500).send({ msg: "Internal  Server Error" });
  }
});

module.exports = router;
