CREATE TABLE IF NOT EXISTS meetings (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(254) NOT NULL,
    description TEXT NOT NULL,
    "creatorId" VARCHAR(50) NOT NULL,
    "creatorEmail" VARCHAR(254) NOT NULL
    );


CREATE TABLE IF NOT EXISTS meetingtimeslots (
    id SERIAL PRIMARY KEY,
    "meetingId" VARCHAR(50) NOT NULL,
    "dateTime" TIMESTAMP NOT NULL,
    CONSTRAINT fk_meetingtimeslots_meetingid_id FOREIGN KEY ("meetingId") REFERENCES meetings(id) ON DELETE RESTRICT ON UPDATE RESTRICT
    );


CREATE TABLE IF NOT EXISTS meetinginvitations (
    id SERIAL PRIMARY KEY,
    "meetingId" VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    CONSTRAINT fk_meetinginvitations_meetingid_id FOREIGN KEY ("meetingId") REFERENCES meetings(id) ON DELETE RESTRICT ON UPDATE RESTRICT
    );
