SELECT 1 from dual;

insert into hotel_app.role values(2,"EMPLOYEE");
insert into hotel_app.role values(1,"CUSTOMER");

insert into room_type values(uuid_short(),"",1,"SINGLE");
insert into room_type values(uuid_short(),"",2,"DOUBLE");
insert into room_type values(uuid_short(),"",3,"TRIPLE");
insert into room_type values(uuid_short(),"",4,"QUAD");
insert into room_type values(uuid_short(),"",6,"SUITE");