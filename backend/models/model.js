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

module.exports = apiModel;
