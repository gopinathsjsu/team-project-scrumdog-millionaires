import React, { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { Grid, Link, Typography } from "@mui/material";
import axios from "axios";
import * as Const from "../constants";

const semiEndpoint = Const.semiEndpoint;

async function deleteBooking(bookingId,setOpenDialog ) {
  setOpenDialog(false)
  console.log(bookingId)
    var request = await axios.delete(semiEndpoint+"/bookings/"+bookingId+"?userId="+sessionStorage.getItem("userId"));
  }
  
export default function DeleteModal({
  open,
  bookingId,
  setOpenDialog,
  
}) {

  return (
    <div>
        <Dialog open={open}>
          <DialogTitle>
            Delete Booking
          </DialogTitle>
          <DialogContent>
            
                  <Typography>
                    <p>
                      Are you sure you want to delete this reservation?
                    </p>
                  </Typography>
                
          </DialogContent>
          <DialogActions>
            <Button
              variant="contained"
              sx={{ textTransform: "none" }}
              onClick={()=>
                setOpenDialog(false)
              }
            >
              {"Cancel"}
            </Button>
            <Button
              variant="contained"
              sx={{ textTransform: "none" }}
              onClick={()=>
                deleteBooking(bookingId,setOpenDialog)
              }
            >
              {"Confirm"}
            </Button>
          </DialogActions>
        </Dialog>
    </div>
  );
}