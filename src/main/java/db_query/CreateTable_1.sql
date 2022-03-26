

 CREATE TABLE "discord_guilds"
("id" INTEGER UNIQUE,
 "d_id"	INTEGER NOT NULL UNIQUE,
 "d_name"	TEXT NOT NULL,
 "d_prefix"	TEXT,"first_noticed"	TEXT,
 PRIMARY KEY("id" AUTOINCREMENT));
