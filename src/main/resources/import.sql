
INSERT INTO user_profile (id, birthdate, email, first_name, is_active, last_name, password, user_name, user_status) VALUES (1,'1990-12-12 00:00:00','meriybaba@gmail.com','Meron',0,'meron','$2a$10$5uwDCD2zXR93c1aBfunJ4ODoceL0OY3QpzRvD.2XVLEWng9NWCV52','meron','Active');
INSERT INTO user_profile (id, birthdate, email, first_name, is_active, last_name, password, user_name, user_status) VALUES (8,'1990-12-12 00:00:00','selam@gmail.com','selam',0,'selam','$2a$10$VuEAYPHHgbdAKyyWjuHTnegWHHiULOFxRkmHTm3BIweAnjOCmraGq','selam','Active');
INSERT INTO user_profile (id, birthdate, email, first_name, is_active, last_name, password, user_name, user_status) VALUES (10,'1999-09-09 00:00:00','amha@gmail.cm','aman',0,'aman','$2a$10$U.hHIGTDSqg7A2u2kapguekIDP3vUgMZY8bxHlLnGBiQqieNThuGq','aman','Active');

INSERT INTO role (id, description, role) VALUES (1,'Admin','Admin');
INSERT INTO role (id, description, role) VALUES (2,'Faculty','Faculty');
INSERT INTO role (id, description, role) VALUES (3,'Student','Student');

INSERT INTO faculty (id, is_availability, user_profile_id) VALUES (7,'\0',8);
INSERT INTO faculty (id, is_availability, user_profile_id) VALUES (9,'\0',10);

INSERT INTO user_profile_roles (user_profile_id, roles_id) VALUES (1,1);
INSERT INTO user_profile_roles (user_profile_id, roles_id) VALUES (10,2);

INSERT INTO specialization (id, specalization) VALUES (1,'SWE Design');
INSERT INTO specialization (id, specalization) VALUES (2,'Compiler');
INSERT INTO specialization (id, specalization) VALUES (3,'DB');



INSERT INTO faculty_specializations (faculty_id, specializations_id) VALUES (9,1);

INSERT INTO entry (id, entry_month, num_of_fpp, num_of_mpp, num_ofusstudents, num_of_fpp_opt, num_of_mpp_opt) VALUES (1,'October',50,50,100,0,0);
INSERT INTO entry (id, entry_month, num_of_fpp, num_of_mpp, num_ofusstudents, num_of_fpp_opt, num_of_mpp_opt) VALUES (2,'February',50,50,100,0,0);
INSERT INTO entry (id, entry_month, num_of_fpp, num_of_mpp, num_ofusstudents, num_of_fpp_opt, num_of_mpp_opt) VALUES (3,'August',50,50,100,0,0);

INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (3,'2017-11-30','November','2017-11-01',1,2,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (4,'2017-01-30','December','2017-01-01',1,3,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (5,'2017-01-30','January','2017-01-01',1,4,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (6,'2017-01-30','Feburary','2017-01-01',1,5,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (7,'2017-01-30','March','2017-01-01',1,6,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (8,'2017-01-30','Aprril','2017-01-01',1,7,100);
INSERT INTO block (id, block_end_date, block_month, block_start_date, entry_id, block_order, num_of_students) VALUES (9,'2017-01-30','May','2017-01-01',1,8,100);

-- INSERT INTO schedule (id,generated_date,status,entry_id)VALUES(1,'1999-09-09 00:00:00','OK',1);
-- 
INSERT   INTO course VALUES (1,555,'Science of Creative Intelligence','SCI',0);
INSERT   INTO course VALUES (1,395,'fundamental programming practices','FPP',1);
INSERT   INTO course VALUES (2,401,'Modern programming practices','MPP',1);
INSERT   INTO course VALUES (3,475,'Web application programming ','WAP',1);
INSERT   INTO course VALUES (4,504,'Alogorithm','Algorithm',1);
INSERT   INTO course VALUES (6,572,'Modern Web Application','MWA',0);
INSERT   INTO course VALUES (7,522,'Big Data','BIGData',0);
INSERT   INTO course VALUES (8,544,'Enterprise Architecture','EA',0);
INSERT   INTO course VALUES (9,425,'Software Engineering','SWE',0);
INSERT   INTO course VALUES (10,525,'Advanced Software Development','ASD',0);
INSERT   INTO course VALUES (11,545,'Web Application Architecture','WAA',0);


INSERT   INTO course_pre_req_course VALUES (2,1);
INSERT   INTO course_pre_req_course VALUES (3,2);
INSERT   INTO course_pre_req_course VALUES (4,2);
INSERT   INTO course_pre_req_course VALUES (6,2);
INSERT   INTO course_pre_req_course VALUES (6,3);
INSERT   INTO course_pre_req_course VALUES (7,2);
INSERT   INTO course_pre_req_course VALUES (7,4);
INSERT   INTO course_pre_req_course VALUES (8,2);
INSERT   INTO course_pre_req_course VALUES (8,3);
INSERT   INTO course_pre_req_course VALUES (9,2);
INSERT   INTO course_pre_req_course VALUES (10,2);
INSERT   INTO course_pre_req_course VALUES (11,2);
INSERT   INTO course_pre_req_course VALUES (11,3);



INSERT INTO course_course_area (course_id, course_area_id) VALUES (1,1);
INSERT INTO course_course_area (course_id, course_area_id) VALUES (3,3);


