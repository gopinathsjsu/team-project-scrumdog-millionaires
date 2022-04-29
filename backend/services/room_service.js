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
}
