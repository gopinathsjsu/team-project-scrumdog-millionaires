const db = require("../dbConnection");
const DB_TABLE_HOTEL = process.env.DB_TABLE_HOTEL || "hotels";
const model = {};

model.getHotels = (table = DB_TABLE_HOTEL) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT *
			FROM ${table}
			`;
    db.query(query, (err, results) => {
      if (err) return reject(err);
      return resolve(results);
    });
  });
};

model.getHotelsByID = (hotel_id, table = DB_TABLE_HOTEL) => {
  return new Promise((resolve, reject) => {
    const query = `
			SELECT * 
			FROM ${table}
			WHERE id = ${hotel_id}
		`;
    db.query(query, (err, hotel) => {
      if (err) return reject(err);
      return resolve(hotel);
    });
  });
};

model.updateRequestactory = (name) => {
  // ADDING extra SPACE after sentence 1 to delimit fields
  const _ = `
		name = '${name}'
	`;
  return _;
};
