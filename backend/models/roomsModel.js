const db = require("../dbConnection");
const DB_TABLE_ROOMS = process.env.DB_TABLE_ROOMS || "rooms";
const model = {};

model.ROOM_ACCOMODATION = {
  single: 1,
  double: 2,
  suites: 4,
};
