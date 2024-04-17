-- CREATE TABLE IF NOT EXISTS "User"
-- (
--     User_ID INT AUTO_INCREMENT NOT NULL,
--     User_Name character varying(300) NOT NULL,
--     Email character varying(300) NOT NULL,
--     Password character varying(200) NOT NULL,
--     Biography character varying(200) NOT NULL,
--     enabled character varying(1) NOT NULL DEFAULT 1,
--     CONSTRAINT User PRIMARY KEY (User_ID)
-- );
CREATE TABLE IF NOT EXISTS "member"
(
    username character varying(50)  NOT NULL,
    realname character varying(300) NOT NULL,
    password character varying(300) NOT NULL,
    email character varying(200) NOT NULL,
    enabled character varying(1) NOT NULL DEFAULT 1,
    CONSTRAINT member PRIMARY KEY (username)
);