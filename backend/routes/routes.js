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
      const results = await apiModel.login(table, email);
      if (results.length > 0) {
        if (hashedPassword(password) === results[0].password) {
          res.json(JSON.parse(JSON.stringify(results[0])));
        } else {
          res.status(401).json({
            error: "Incorrect Password",
          });
        }
      } else {
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

router.post("/register", async (req, res, next) => {
  try {
    console.log(req.body.password);
    let personaType = req.body.personaType;
    delete req.body.personaType;
    req.body.password = hashedPassword(req.body.password);
    let table = await checkPersona(personaType, res);
    console.log(table);
    if (!table) res.status(500).send("Persona not specified.");
    else {
      console.log(req.body);

      await apiModel.register(req.body, table);
      res.writeHead(200, {
        "Content-Type": "text/plain",
      });
      res.end("Success");
    }
  } catch (e) {
    console.error(e);
    res.status(500).json({
      error: e,
    });
  }
});
