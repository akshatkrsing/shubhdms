-- all the tables and corresponding fields
use dms;
create table if not exists patient(registrationNo int primary key, firstName varchar(15),lastName
 varchar(15), emailID varchar(50),password varchar(50), lastActive
 datetime, profilePic longblob);

 create table if not exists bulletins(bulletinID int primary key auto_increment,caption varchar(150), topic
 varchar(20), bulletinTime timestamp, teacherID int,image longblob);

 create table if not exists appointmens(appointmentID int primary key auto_increment, registrationNo int,doctorID int, memo varchar(150),
 appointmentStatus tinyint,confirmationTime time, appointmentDate date,fromTime time, toTime time,foreign key(registrationNo)
 references patient(registrationNo) on delete cascade,foreign key(doctorID) references doctor(doctorID) on delete set null);

 create table if not exists doctor(doctorID int primary key, doctorName varchar(20), doctorType varchar(20),doctorImage longblob);

 create table if not exists dutyChart(dutyChartID int primary key auto_increment,dateUpload timestamp, dutyChartDocx longblob);