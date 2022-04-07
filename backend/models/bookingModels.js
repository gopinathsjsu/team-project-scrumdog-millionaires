const db = require("../dbConnection");
const DB_TABLE_BOOKINGS = process.env.DB_TABLE_BOOKINGS || "bookings";
const model = {};

model.getHotelBookings = (table = DB_TABLE_BOOKINGS) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT 
				b.*, 
				c.first_name as user_first_name, c.last_name as user_last_name,
				r.name as room_name, r.room_type as room_type
			FROM ${table} as b
				JOIN customers as c
					on b.user_id = c.id
				JOIN rooms as r
					on b.room_id = r.id
		`;
    db.query(query, (err, results) => {
      if (err) return reject(err);
      return resolve(results);
    });
  });
};
