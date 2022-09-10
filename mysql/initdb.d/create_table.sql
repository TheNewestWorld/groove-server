CREATE TABLE user
(
    id       INT         NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    password  VARCHAR (50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    nickname  VARCHAR(50) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    last_login_at TIMESTAMP NOT NULL,
    deleted_flag TINYINT(1) NOT NULL,
    authenticated_flag TINYINT(1) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE category_group
(
    id         INT       NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name       CHAR(10)  NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE category
(
    id                    INT       NOT NULL AUTO_INCREMENT,
    created_at            TIMESTAMP NOT NULL,
    updated_at            TIMESTAMP NOT NULL,
    name                  CHAR(10)  NOT NULL,
    ref_category_group_id INT       NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(ref_category_group_id) REFERENCES category_group(id)
);

CREATE TABLE post
(
    id              INT          NOT NULL AUTO_INCREMENT,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP    NOT NULL,
    title           VARCHAR(100) NOT NULL,
    content         MEDIUMTEXT   NOT NULL,
    like_count      INT,
    temporary_flag  TINYINT(1) NOT NULL,
    ref_user_id     INT          NOT NULL,
    ref_category_id INT      NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(ref_user_id) REFERENCES user(id),
    FOREIGN KEY(ref_category_id) REFERENCES category(id)
);

