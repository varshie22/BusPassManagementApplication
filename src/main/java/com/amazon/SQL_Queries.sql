create database BusPassManagementDB;

use BusPassManagementDB;

  create table User(
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(256),
      phone VARCHAR(256),
      email VARCHAR(256),
      password VARCHAR(256),
      address VARCHAR(256),
      department VARCHAR(256),
      type INT,
      createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
  );

  create table Feedback(
  	id INT PRIMARY KEY AUTO_INCREMENT,
  	userId INT,
  	title VARCHAR(256),
  	description VARCHAR(2048),
  	type INT CHECK (type>=1 and type<=3),
  	raisedBy VARCHAR(256),
  	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
  	FOREIGN KEY (userId) REFERENCES User(id)
  );

  create table Route(
  	 id INT PRIMARY KEY AUTO_INCREMENT,
  	 title VARCHAR(256),
  	 description VARCHAR(256),
  	 adminId INT,
  	 createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
  	 FOREIGN KEY (adminId) REFERENCES User(id)
   );

    create table BusPass(
        id INT PRIMARY KEY AUTO_INCREMENT,
        uid INT,
        routeId INT,
        requestedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
        approvedRejectedOn DATETIME,
        validTill DATETIME,
        status INT DEFAULT 1,
        createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (uid) REFERENCES User(id),
        FOREIGN KEY (routeId) REFERENCES Route(id)
    );
  create table Stop(
  	 id INT PRIMARY KEY AUTO_INCREMENT,
  	 address VARCHAR(256),
  	 sequenceOrder VARCHAR(256),
  	 routeId INT,
  	 adminId INT,
  	 createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
  	 CONSTRAINT UC_stop UNIQUE (routeId,sequenceOrder),
  	 FOREIGN KEY (routeId) REFERENCES Route(id) ON DELETE CASCADE,
  	 FOREIGN KEY (adminId) REFERENCES User(id)
  	);

  create table Vehicle(
   	id INT PRIMARY KEY AUTO_INCREMENT,
   	registrationNumber VARCHAR(256),
   	totalSeats INT,
   	filledSeats INT,
   	routeId INT,
   	type INT CHECK (type>=1 and type<=2),
   	vehicleStatus INT CHECK (vehicleStatus>=1 and vehicleStatus<=2),
   	startPickUpTime VARCHAR(256),
   	startDropOffTime VARCHAR(256),
   	adminId INT,
   	driverId INT,
   	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
   	FOREIGN KEY (routeId) REFERENCES Route(id)  ON DELETE CASCADE,
   	FOREIGN KEY (adminId) REFERENCES User(id),
   	FOREIGN KEY (driverId) REFERENCES User(id)
  );
  INSERT INTO User (name, phone, email, password , address, department, type)
                  VALUES ("Varsha", "963258741", "varsha@abc.com", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", "Jairampur colony", "Admin", 1),
                  ("Sam", "969854712", "sam@abc.com", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", "Palasia square", "Dev", 2),
                  ("Hector", "98675411", "hector@abc.com", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", "Tower square", "User", 2),
                  ("Naksh", "958614237", "naksh@abc.com", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", "Collector Office", "User", 2),
                  ("Rubina", "986734121", "rubina@abc.com", "pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=", "Palsikar colony", "User", 2);