CREATE TABLE IF NOT EXISTS "member"
(
    User_ID character varying(50) NOT NULL,
    User_Name character varying(300) NOT NULL,
    Email character varying(300) NOT NULL,
    Password character varying(200) NOT NULL,
    Biography character varying(200) NOT NULL,
    enabled character varying(1) NOT NULL DEFAULT '1',
    CONSTRAINT "member" PRIMARY KEY (User_ID)
);
