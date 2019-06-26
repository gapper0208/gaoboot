CREATE TABLE USER
(
   id INT PRIMARY KEY AUTO_INCREMENT,
   NAME VARCHAR(20),
   birthday DATE,
   money DOUBLE
);

CREATE TABLE dept
(
   did INT PRIMARY KEY AUTO_INCREMENT,
   dname VARCHAR(20)
);

CREATE TABLE emp
(
   eid INT PRIMARY KEY AUTO_INCREMENT,
   ename VARCHAR(20),
   hiredate DATE,
   sal DECIMAL(10,2),
   dept_id INT
);

ALTER TABLE emp
ADD CONSTRAINT FK FOREIGN KEY(dept_id)
REFERENCES dept(did);