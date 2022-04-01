const express = require("express");
const router = express.Router();

router.get("/health", async (req, res, next) => {
  try {
    res.json(JSON.parse(JSON.stringify("I'm Up!")));
  } catch (e) {
    console.error(e);
    res.status(500).json({
      error: "Internal Server Error: Please try again",
    });
  }
});

const checkPersona = (personaType, res) => {
  if (personaType.toLowerCase() === "cu") {
    return "customers";
  } else if (personaType.toLowerCase() === "ho") {
    return "hotels";
  } else {
    return;
  }
};

router.post("/login", async (req, res, next) => {
  try {
    const { personaType, email, password } = req.body;
    let table = await checkPersona(personaType, res);
    if (!table) res.status(500).send("Persona not specified.");
    else {
      // Invoke the query
      const results = await apiModel.login(table, email);
      if (results.length > 0) {
        if (hashedPassword(password) === results[0].password) {
          // Return the response
          res.json(JSON.parse(JSON.stringify(results[0])));
        } else {
          // Auth Error
          res.status(401).json({
            error: "Incorrect Password",
          });
        }
      } else {
        // Auth Error
        res.status(401).json({
          error: "Incorrect Email or Password",
        });
      }
    }
  } catch (e) {
    console.error(e);
    res.status(500).json({
      error: "Internal Server Error: Please try again",
    });
  }
});
