DROP TABLE IF EXISTS USERS ;

CREATE TABLE USERS (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NULL,
  `userId` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `pic` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`Milestone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS MILESTONE ;

CREATE TABLE MILESTONE (
  `id` INT NOT NULL auto_increment,
  `subject` VARCHAR(45) NULL,
  `startDate` DATETIME NULL,
  `endDate` DATETIME NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `mydb`.`Issue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ISSUE ;

CREATE TABLE ISSUE (
  `id` INT NOT NULL auto_increment,
  `milestoneId` INT DEFAULT 0,
  `writerId` INT NOT NULL,
  `labelId` INT DEFAULT 0,
  `assigneeId` INT DEFAULT 0,
  `subject` VARCHAR(45) NULL,
  `comment` LONGTEXT NULL,
  `isOpen` BOOLEAN DEFAULT true,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`History`
-- -----------------------------------------------------
DROP TABLE IF EXISTS HISTORY ;

CREATE TABLE HISTORY (
  `id` INT NOT NULL auto_increment,
  `issueId` INT NULL,
  `writerId` INT NULL,
  `content` LONGTEXT NULL,
  `date` DATETIME NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`Label`
-- -----------------------------------------------------
DROP TABLE IF EXISTS LABEL ;

CREATE TABLE LABEL (
  `id` INT NOT NULL auto_increment,
  `labelText` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  

INSERT INTO USERS (userId, name, password, pic) VALUES('testUser','javajigi','test','https://avatars2.githubusercontent.com/u/520500?v=3&s=140');
INSERT INTO USERS (userId, name, password, pic) VALUES('testUser2','cca-company','test','https://avatars1.githubusercontent.com/u/7744966?v=3&u=1bd7d2e72800c0d5a1a5a2e04da851d30b3c13c7&s=140');
INSERT INTO USERS (userId, name, password, pic) VALUES('testUser3','진저애비','test','https://avatars3.githubusercontent.com/u/14341811?v=3&s=400');
INSERT INTO USERS (userId, name, password, pic) VALUES('testUser4','신영','test','https://avatars1.githubusercontent.com/u/7744615?v=3&s=400');
INSERT INTO USERS (userId, name, password, pic) VALUES('testUser5','전용우','test','https://avatars3.githubusercontent.com/u/22300?v=3&s=400');

INSERT INTO MILESTONE (subject, startDate) VALUES('Milestone1',CURRENT_TIMESTAMP);
INSERT INTO MILESTONE (subject, startDate) VALUES('Milestone2',CURRENT_TIMESTAMP);
INSERT INTO MILESTONE (subject, startDate) VALUES('Milestone3',CURRENT_TIMESTAMP);

INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(1,1,'testIssue','comment here!',true,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(1,5,'testIssue2','comment here!',false,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(2,4,'testIssue3','comment here!',false,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(2,1,'testIssue4','comment here!',true,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(3,2,'testIssue5','comment here!',true,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(3,3,'testIssue6','comment here!',false,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(1,1,'testIssue7','comment here!',true,CURRENT_TIMESTAMP);
INSERT INTO ISSUE (milestoneId, writerId, subject, comment, isOpen, date) VALUES(2,2,'testIssue8','comment here!',true,CURRENT_TIMESTAMP);

INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(1,1,'changed','changed milestone 1',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(1,2,'comment','comment new issue!',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(1,3,'file','upload file',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(2,1,'changed','changed milestone 1',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(2,2,'comment','comment new issue!',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(2,3,'file','upload file',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(3,1,'changed','changed milestone 1',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(3,2,'comment','comment new issue!',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(3,3,'file','upload file',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(4,1,'changed','changed milestone 1',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(4,2,'comment','comment new issue!',CURRENT_TIMESTAMP);
INSERT INTO HISTORY (issueId, writerId, type, content, date) VALUES(4,3,'file','upload file',CURRENT_TIMESTAMP);

INSERT INTO LABEL (labelText) VALUES('label1');
INSERT INTO LABEL (labelText) VALUES('label2');
INSERT INTO LABEL (labelText) VALUES('label3');
INSERT INTO LABEL (labelText) VALUES('label4');
INSERT INTO LABEL (labelText) VALUES('label5');

