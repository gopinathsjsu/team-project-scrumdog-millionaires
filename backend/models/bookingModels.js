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

model.getUserBookings = (userId, table = DB_TABLE_BOOKINGS) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT 
				b.*, 
				r.name as room_name, r.room_type as room_type
			FROM ${table} as b
				JOIN rooms as r
					on b.room_id = r.id
			WHERE user_id = '${userId}'
			AND
			status IN ('confirmed', 'created')
		`;
    db.query(query, (err, hotel) => {
      if (err) return reject(err);
      return resolve(hotel);
    });
  });
};

model.create = (
  user_id,
  room_id,
  price,
  from_date,
  to_date,
  guest_count,
  status,
  table = DB_TABLE_BOOKINGS
) => {
  return new Promise((resolve, reject) => {
    from_date = from_date.toISOString().replace("Z", "");
    to_date = to_date.toISOString().replace("Z", "");

    const query = `
			INSERT
			INTO ${table} 
			(room_id, user_id, price, from_date, to_date, guest_count, status)
			VALUES 
			('${room_id}', '${user_id}', '${price}', '${from_date}', '${to_date}', '${guest_count}', '${status}');
		`;

    db.query(query, (err, booking) => {
      if (err) return reject(err);
      resolve(booking);
    });
  });
};

model.getByID = (bookingID, userId, table = DB_TABLE_BOOKINGS) => {
  return new Promise((resolve, reject) => {
    // if userId {};

    const query = `
			SELECT *
			FROM ${table}
			WHERE id = '${bookingID}'
		`;
    db.query(query, (err, booking) => {
      if (err) return reject(err);
      resolve(booking);
    });
  });
};
