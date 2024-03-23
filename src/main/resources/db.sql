CREATE TABLE auth_user (
    id VARCHAR(255) PRIMARY KEY,
    uuid VARCHAR(255),
    username VARCHAR(255),
    auth_type VARCHAR(255),
    phone_verified BOOLEAN,
    email_verified BOOLEAN,
    token VARCHAR(255),
    created_at VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    modified_at VARCHAR(255) NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);

ALTER TABLE auth_user MODIFY COLUMN id INT AUTO_INCREMENT;

CREATE TABLE otp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    communicated_to VARCHAR(255),
    verification_mode VARCHAR(255),
    otp VARCHAR(255),
    service_provider VARCHAR(255),
    status VARCHAR(255),
    expiry_time VARCHAR(255),
    purpose VARCHAR(255),
    created_at VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    modified_at VARCHAR(255) NOT NULL,
    modified_by VARCHAR(255),
    deleted BOOLEAN
);
