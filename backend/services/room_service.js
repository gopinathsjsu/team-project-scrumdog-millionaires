const model = require("./../models/roomsModel");
const { HTTP_500, HTTP_RES } = require("./../Utilities/http_utils");
class RoomService {
  async getRooms(roomId) {
    try {
      const rooms =
        roomId === undefined
          ? await model.getRooms()
          : await model.getRoomsByRoomID(roomId);
      return {
        status: 200,
        data: rooms,
        msg: `Fetched rooms`,
      };
    } catch (err) {
      console.error(
        `RoomService::getRooms/${roomId}::Uncaught exception\n, ${err}`
      );
      return HTTP_500();
    }
  }
  async createRoom(newRoomObj) {
    const { name, basePrice, roomType, minGuests } = newRoomObj;

    if (!basePrice || !(name in model.ROOM_ACCOMODATION) || minGuests == 0) {
      return HTTP_RES(400, "Bad Request: Missing room information");
    }

    const newRoomObject = {
      id: new Date().getTime() % 100000,
      name,
      base_price: basePrice,
      room_type: roomType,
      min_guests: minGuests || model.ROOM_ACCOMODATION[roomType],
    };

    await model.create(model.createRoomFactory(newRoomObject));

    const response = await model.getRoomsByRoomID(newRoomObject.id);
    return HTTP_RES(200, "Success", response);
  }

  async update(roomId, updateObj) {
    // @TODO: Authorization
    const rooms = await model.getRoomsByRoomID(roomId);
    if (!(Array.isArray(rooms) && rooms.length > 0))
      return HTTP_RES(404, "No room found");

    const { name, basePrice, minGuests, weekEndSurge, festivalSurge } =
      updateObj;
    const [room] = rooms;

    await model.updateByID(
      roomId,
      model.updateRequestactory(
        name || room.name,
        basePrice || room.base_price,
        minGuests || room.min_guests,
        weekEndSurge || room.week_end_surge,
        festivalSurge || room.festival_surge
      )
    );

    const updated = await model.getRoomsByRoomID(roomId);
    return HTTP_RES(200, "Success", updated);
  }
}

module.exports = RoomService;
