import { StrictMode } from "react";
import ReactDOM from "react-dom";

import WebFont from "webfontloader";
import { RegisterView1 } from "./RegisterView1";
WebFont.load({
  google: {
    families: ["Poppins", "Roboto:400,700", "undefined:"],
  },
});

const rootElement = document.getElementById("root");
ReactDOM.render(
  <StrictMode>
    <App />
  </StrictMode>,
  rootElement
);
