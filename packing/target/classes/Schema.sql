CREATE TABLE packs
(
    ID serial  primary key,
    PICK_ID integer null,
    BATCH_NBR character varying(20),
    BUS_NAME character varying(50),
	LOCN_NBR integer not null default 0,
	BUS_UNIT  character varying(3) NOT NULL,
	COMPANY  character varying(10),
	DIVISION  character varying(10),
    ITEM_BRCD character varying(25)  NOT NULL,
    QTY integer NOT NULL DEFAULT 0,
    PACKED_QTY integer DEFAULT 0,
    FROM_CONTAINER_NBR  character varying(25),
    TO_CONTAINER_NBR  character varying(25),
    STAT_CODE integer DEFAULT 0,
    ORDER_ID   INTEGER NOT NULL,
    ORDER_NBR  character varying(25) NOT NULL,
	ORDER_LINE_NBR  INTEGER NOT NULL,
	SINGLES character varying(1) NULL,
	PACKAGE_NBR  character varying(25) NOT NULL,
	TRANSACTION_NAME character varying(50),
	SOURCE character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp default NOW(),
    UPDATED_DTTM  timestamp default NOW(),
    USER_ID character varying(25)
);