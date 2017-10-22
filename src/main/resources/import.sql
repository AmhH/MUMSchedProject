
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



INSERT INTO course_course_area (course_id, course_area_id) VALUES (1,1);
INSERT INTO course_course_area (course_id, course_area_id) VALUES (3,3);


INSERT INTO faculty_specializations (faculty_id, specializations_id) VALUES (9,1);

