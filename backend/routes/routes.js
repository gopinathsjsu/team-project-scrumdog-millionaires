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
