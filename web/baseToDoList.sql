CREATE TABLE todo (
	id_todo SERIAL,
	id_service INTEGER,
	todo VARCHAR(100),
	start_date TIMESTAMP,
	deadline TIMESTAMP,
	status INTEGER,
	FOREIGN KEY (id_service) REFERENCES service(id_service)
);

CREATE TABLE under_task (
	id_under_task SERIAL,
	id_employe INTEGER,
	id_todo INTEGER,
	under_task VARCHAR(100),
	status INTEGER,
	FOREIGN KEY(id_employe) REFERENCES employe(id_employe),
	FOREIGN KEY(id_todo) REFERENCES todo(id_todo)
);


