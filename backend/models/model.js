var db = require("../dbConnection");
var pool = require("../queryBuilder");

let apiModel = {};

// Login
apiModel.login = (table, email) => {
  return new Promise((resolve, reject) => {
    // SQL query to fetch user details
    let query =
      "select t.id, t.email, t.password, t.first_name, t.last_name " +
      "from " +
      table +
      " t where t.email = '" +
      email +
      "'";
    db.query(query, (err, results) => {
      if (err) return reject(err);
      return resolve(results);
    });
  });
};

apiModel.register = (dataJson, table) => {
  return new Promise((resolve, reject) => {
    let query =
      "select * from " + table + " where email = '" + dataJson.email + "'";
    console.log(query);
    db.query(query, (err, results) => {
      if (err) return reject(err);
      if (results.length === 0) {
        console.log(results);
        query =
          "insert into " +
          table +
          " (first_name, last_name, email, password) values ('" +
          dataJson.first_name +
          "', '" +
          dataJson.last_name +
          "', '" +
          dataJson.email +
          "', '" +
          dataJson.password +
          "')";
        console.log(query);
        db.query(query, (err, results) => {
          if (err) return reject(err);
          return resolve(results);
        });
      } else {
        reject("Email already exists");
      }
    });
  });
};

module.exports = apiModel;
