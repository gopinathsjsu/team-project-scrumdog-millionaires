const db = require("../dbConnection");
const DB_TABLE_ROOMS = process.env.DB_TABLE_ROOMS || "rooms";
const model = {};

model.ROOM_ACCOMODATION = {
  single: 1,
  double: 2,
  suites: 4,
};

model.getRoomsByRoomID = (roomId, table = DB_TABLE_ROOMS) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT *
			FROM ${table}
			WHERE id = '${roomId}'
			`;
    console.info("Query = ", query);
    db.query(query, (err, results) => {
      if (err) return reject(err);
      return resolve(results);
    });
  });
};

model.getRooms = (table = DB_TABLE_ROOMS) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT * 
			FROM ${table}
		`;
    console.info("QUERY =", query);

    db.query(query, (err, hotel) => {
      if (err) return reject(err);
      return resolve(hotel);
    });
  });
};
