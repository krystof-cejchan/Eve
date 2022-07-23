CREATE TABLE "public_playlists" (
	"id"	INTEGER NOT NULL UNIQUE,
	"title"	TEXT NOT NULL,
	"guild_id"	INTEGER NOT NULL,
	"author"	TEXT NOT NULL,
	"songs"	TEXT NOT NULL,
	"popularity"	INTEGER DEFAULT 0,
	"played_n"	INTEGER DEFAULT 0,
	PRIMARY KEY("id" AUTOINCREMENT)
);