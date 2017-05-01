CREATE USER 'user_ncs'@'%' ;
UPDATE mysql.user SET Password=PASSWORD('user_ncs') WHERE User='user_ncs' AND Host='%' ;
GRANT Select ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Insert ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Delete ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Update ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Alter ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Create ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Create view ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Drop ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Grant option ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Index ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT References ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Show view ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Trigger ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Alter routine ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Create routine ON ncs_erp.* TO 'user_ncs'@'%' ;
GRANT Execute ON ncs_erp.* TO 'user_ncs'@'%' ;
FLUSH PRIVILEGES ;

DROP USER 'user_ncs'@'%' ;

load data local infile 'D:\\workspace_exam\\kdu_swexam\\DataFiles\\department.txt' 
into table department
character set 'UTF8'
fields terminated by ','
lines terminated by '\n';

drop table employee;
drop table title;
drop table department;