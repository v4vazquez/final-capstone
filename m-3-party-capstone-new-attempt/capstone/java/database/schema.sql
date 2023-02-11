BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, events, songs, event_songs;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE events (
    event_id SERIAL,
    event_name varchar(50) NOT NULL,
    description text NOT NULL,
    host_id int,
    dj_id int,
    CONSTRAINT PK_event_id PRIMARY KEY (event_id),
    CONSTRAINT FK_host_id FOREIGN KEY (host_id) REFERENCES users(user_id),
    CONSTRAINT FK_dj_id FOREIGN KEY (dj_id) REFERENCES users(user_id)
);

CREATE TABLE songs (
    song_id varchar(30) NOT NULL,
    name varchar(50),
    artist varchar(30),
    picture varchar(100),
    CONSTRAINT PK_song_id PRIMARY KEY (song_id)
);

CREATE TABLE event_songs (
    song_id varchar(50) NOT NULL,
    event_id int NOT NULL,
    suggested varchar(20),
    likes int DEFAULT 0,
    CONSTRAINT PK_song_event PRIMARY KEY(song_id, event_id),
    CONSTRAINT FK_event_song_song_id FOREIGN KEY(song_id) REFERENCES songs(song_id),
    CONSTRAINT FK_event_song_event_id FOREIGN KEY(event_id) REFERENCES events(event_id)
);




COMMIT TRANSACTION;