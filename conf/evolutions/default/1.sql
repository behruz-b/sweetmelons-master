# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "ACCOUNT" ("ID" SERIAL NOT NULL PRIMARY KEY,"FIRST_NAME" VARCHAR(254) NOT NULL,"LAST_NAME" VARCHAR(254) NOT NULL,"ADDRESS" VARCHAR(254) DEFAULT '' NOT NULL,"EMAIL" VARCHAR(254) DEFAULT '' NOT NULL,"PASSWORD" VARCHAR(254) NOT NULL,"ROLE" INTEGER DEFAULT 2 NOT NULL,"SCORE" DOUBLE PRECISION DEFAULT 0.0 NOT NULL);
create table "ADMIN" ("ID" SERIAL NOT NULL PRIMARY KEY,"FIRST_NAME" VARCHAR(254) NOT NULL,"LAST_NAME" VARCHAR(254) NOT NULL,"LOGIN" VARCHAR(254) NOT NULL,"PASSWORD" VARCHAR(254) NOT NULL);
create table "Questions" ("ID" SERIAL NOT NULL PRIMARY KEY,"question" VARCHAR(254) DEFAULT '' NOT NULL,"aVariant" VARCHAR(254) DEFAULT '' NOT NULL,"bVariant" VARCHAR(254) DEFAULT '' NOT NULL,"cVariant" VARCHAR(254) DEFAULT '' NOT NULL,"dVariant" VARCHAR(254) DEFAULT '' NOT NULL);

# --- !Downs

drop table "Questions";
drop table "ADMIN";
drop table "ACCOUNT";

