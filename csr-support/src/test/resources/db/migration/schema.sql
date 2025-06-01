CREATE SCHEMA IF NOT EXISTS test_schema;

CREATE TABLE IF NOT EXISTS test_schema.test_entity
(
    id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    text
    VARCHAR
(
    255
) NOT NULL,
    version INT NOT NULL,
    created_at TIMESTAMP
(
    6
) WITH TIME ZONE,
      created_by VARCHAR (255),
    updated_at TIMESTAMP
(
    6
)
  WITH TIME ZONE,
      updated_by VARCHAR (255),
    );
