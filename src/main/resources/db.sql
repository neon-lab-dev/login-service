CREATE TABLE otp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    communicated_to VARCHAR(255),
    verification_mode VARCHAR(255),
    otp VARCHAR(255),
    service_provider VARCHAR(255),
    status VARCHAR(255),
    expiry_time DATETIME,
    purpose VARCHAR(255),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255),
    modified_at DATETIME NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);

CREATE INDEX idx_communicated_to ON otp (communicated_to);
CREATE INDEX idx_status ON otp (status);


-----------------------------------------------------------------

CREATE TABLE auth_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    user_name VARCHAR(255),
    auth_type VARCHAR(255),
    device_id VARCHAR(255),
    phone_verified BOOLEAN DEFAULT false,
    email_verified BOOLEAN DEFAULT false,
    token VARCHAR(255),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255),
    modified_at DATETIME NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);

CREATE INDEX idx_user_id ON auth_user (user_id);
CREATE INDEX idx_user_name ON auth_user (user_name);
CREATE INDEX idx_auth_type ON auth_user (auth_type);


-----------------------------------------------------------------

CREATE TABLE system_config (
    id INT AUTO_INCREMENT PRIMARY KEY,
    config_key VARCHAR(255),
    config_value VARCHAR(255),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255),
    modified_at DATETIME NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);

CREATE INDEX idx_config_key ON system_config (config_key);


INSERT INTO login_db.system_config (id, config_key, config_value, created_at, created_by, modified_at, modified_by, deleted)
VALUES(1, 'sms.provider', 'default', '2024-03-23 18:51:39', '', '2024-03-23 18:51:39', '', 0);
INSERT INTO login_db.system_config (id, config_key, config_value, created_at, created_by, modified_at, modified_by, deleted)
VALUES(2, 'email.provider', 'default', '2024-03-23 18:51:56', '', '2024-03-23 18:51:56', '', 0);
INSERT INTO login_db.system_config (id, config_key, config_value, created_at, created_by, modified_at, modified_by, deleted)
VALUES(3, 'sms.otp.expiry.minutes', '5', '2024-03-23 18:52:25', '', '2024-03-23 18:52:25', '', 0);
INSERT INTO login_db.system_config(id, config_key, config_value, created_at, created_by, modified_at, modified_by, deleted)
VALUES(4, 'email.otp.expiry.minutes', '5', '2024-03-23 18:52:35', '', '2024-03-23 18:52:35', '', 0);

-----------------------------------------------------------------


CREATE TABLE user (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    primary_phone_no VARCHAR(255) NOT NULL,
    secondary_phone_no VARCHAR(255),
    created_at DATETIME NOT NULL,
    created_by VARCHAR(255),
    modified_at DATETIME NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);

CREATE INDEX idx_primary_phone ON user (primary_phone_no);
CREATE INDEX idx_email ON user (email);



