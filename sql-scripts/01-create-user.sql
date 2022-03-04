CREATE USER 'gym'@'localhost' IDENTIFIED BY 'gym';
GRANT ALL PRIVILEGES ON * . * TO 'gym'@'localhost';
alter user 'gym'@'localhost' identified by 'gym123';