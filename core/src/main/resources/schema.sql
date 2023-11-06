CREATE TABLE USERS (
  USER_ID NUMBER NOT NULL PRIMARY KEY,
  NAME VARCHAR2(100 BYTE) NOT NULL,
  EMAIL VARCHAR2(200 BYTE) NOT NULL
);

CREATE TABLE USER_DEVICE (
  USER_ID NUMBER NOT NULL,
  DEVICE_HASH VARCHAR2(32 BYTE) NOT NULL,
  FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID)
);

CREATE TABLE USER_TOKEN (
  USER_ID NUMBER NOT NULL,
  TOKEN VARCHAR2(120 BYTE) NOT NULL,
  FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID)
);

CREATE TABLE USER_BANKCARD (
  USER_ID NUMBER NOT NULL,
  CARD_ID VARCHAR2(5 BYTE) NOT NULL,
  CARD_NUMBER NUMBER NOT NULL,
  CVC NUMBER NOT NULL,
  AMOUNT NUMBER NOT NULL,
  CURRENCY VARCHAR2(3 BYTE) NOT NULL,
  FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID)
);