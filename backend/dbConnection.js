var mysql = require("mysql2");
require("dotenv").config();
var con = mysql.createPool({
  connectionLimit: 500,
  host: "hotel-booking.cgxj9rhnct9k.us-east-1.rds.amazonaws.com",
  user: "admin",
  password: "Avalon321",
  database: "hotelbooking",
});

module.exports = con;
