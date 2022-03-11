import { StrictMode } from "react";
import ReactDOM from "react-dom";

import WebFont from "webfontloader";
import { RegisterView } from "./registrationView";

WebFont.load({
  google: {
    families: ["Poppins", "Roboto:400,700", "undefined:"],
  },
});

const rootElement = document.getElementById("root");
ReactDOM.render(
  <StrictMode>
    <RegisterView />
  </StrictMode>,
  rootElement
);
