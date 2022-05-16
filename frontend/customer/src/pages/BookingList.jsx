import React, { useState, useEffect } from "react";
import {} from "react-icons/fa";
import axios from "axios";
import Title from './Title';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import DeleteModal from "./DeleteModal";
import EditModal from "./EditModal";
import { TableHead } from "@mui/material";
import * as Const from "../constants";

const semiEndpoint = Const.semiEndpoint;

var bookingsAPI =semiEndpoint+"/bookings/users/"+sessionStorage.getItem("userId"); 

// const awsEndpoint = "18.212.225.65";
function BookList() {

  var [openDeleteDialog, setOpenDeleteDialog] = useState(false);
  var [openEditDialog, setOpenEditDialog] = useState(false);
  var [bookingId, setBookingId] = useState(0);
  var [data , setData]=useState('');
  var [fromDate, setFromDate] = useState('')
  var [toDate, setToDate] = useState('')

  function onCancel(id){
    setBookingId(id);
    setOpenDeleteDialog(true);
  }

  function onEdit(id, fromDate, toDate){
    setOpenEditDialog(id);
    setFromDate(fromDate);
    setToDate(toDate);
    setBookingId(id);
  }

  useEffect(() => {
    async function fetchBookings() {
        var request = await axios.get(bookingsAPI);
        console.log(request.data);
        setData(request.data.data);
      }

    fetchBookings();
  }, []);

  return (
    
    <div >
        <section className="single-room container">
    <Title title="Bookings"></Title>
</section>
     <TableContainer component={Paper} >
     <Table stickyHeader aria-label="sticky table">
       {/* <TableHead>
         <TableCell>Room Name</TableCell>
         <TableCell>Status</TableCell>
         <TableCell>From Date</TableCell>
         <TableCell>To Date</TableCell>
         <TableCell>Price</TableCell>
         <TableCell colSpan={2}>Actions</TableCell>
       </TableHead> */}
       <TableBody style={{width: "100%"}}>
          
         {data && data.map((row) => (
           <TableRow
             key={row.id}
             style={{height: 'auto !important'}}
            //  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
           >
             <TableCell>
               {row.room_name}
             </TableCell>
             <TableCell>{row.status}</TableCell>
             <TableCell>{row.from_date.substring(0, 10)}</TableCell>
             <TableCell>{row.to_date.substring(0, 10)}</TableCell>
             <TableCell>{row.price}</TableCell>
             <TableCell><DeleteIcon onClick={() => onCancel(row.id)}></DeleteIcon></TableCell>
             <TableCell><EditIcon onClick={() => onEdit(row.id, )}></EditIcon></TableCell>
           </TableRow>
         ))}
       </TableBody>
     </Table>
   </TableContainer>

   <DeleteModal
                open={openDeleteDialog}
                bookingId={bookingId}
                setOpenDialog={setOpenDeleteDialog}
              />

<EditModal
                open={openEditDialog}
                bookingId={bookingId}
                setOpenDialog={setOpenEditDialog}
              />
   </div>

  );
}

export default BookList;
