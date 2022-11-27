drop table if exists "MOVIE" CASCADE;
create sequence IF NOT EXISTS hibernate_sequence start with 1 increment by 1;

CREATE TABLE "MOVIE" (
    "ID" bigint not null, 
    "YEAR" varchar(256),
    "TITLE" varchar(256),
    "STUDIOS" varchar(256),
    "PRODUCERS" varchar(256),
    "ISWINNER" varchar(1),
     primary key ("ID")
);
