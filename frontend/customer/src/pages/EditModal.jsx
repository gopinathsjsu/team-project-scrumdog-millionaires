import React, { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { Grid, Link, Typography } from "@mui/material";
import axios from "axios";
import DatePicker from "react-datepicker";
import * as Const from "../constants"


const semiEndpoint = Const.semiEndpoint;
async function editBooking(bookingId,setOpenDialog, startDate, endDate ) {
  setOpenDialog(false)
  console.log(bookingId)
    var request = await axios.put(semiEndpoint+"/bookings/"+bookingId+"?userId="+sessionStorage.getItem("userId"), {start: startDate, end: endDate});
  }

export default function EditModal({
    fromDate,
    toDate,
  open,
  bookingId,
  setOpenDialog,
  
}) {

    var [startDate, setStartDate] = useState(fromDate);
    var [endDate, setEndDate] = useState(toDate);

  return (
    <div>
        <Dialog open={open}>
          <DialogTitle>
            Edit Booking
          </DialogTitle>
          <DialogContent>
          <label htmlFor="Fromdate" className="font-weight-bolder mr-3">From Date </label>
          <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
          
          <label htmlFor="Fromdate" className="font-weight-bolder mr-3">To Date </label>
          <DatePicker selected={endDate} onChange={(date) => setEndDate(date)} />
                
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
                editBooking(bookingId,setOpenDialog, startDate, endDate)
              }
            >
              {"Confirm"}
            </Button>
          </DialogActions>
        </Dialog>
    </div>
  );
}