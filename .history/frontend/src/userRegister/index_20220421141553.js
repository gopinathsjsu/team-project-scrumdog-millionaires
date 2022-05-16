import { StrictMode } from "react";
import ReactDOM from "react-dom";

import WebFont from "webfontloader";
import { RegisterView1 } from "./registrationView";

WebFont.load({
  google: {
    families: ["Poppins", "Roboto:400,700", "undefined:"],
  },
});

const rootElement = document.getElementById("root");
ReactDOM.render(
  <StrictMode>
    <RegisterView1 />
  </StrictMode>,
  rootElement
);
