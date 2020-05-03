DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id INT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL NOT NULL,
  type VARCHAR(6) NOT NULL,
  effectiveDate TIMESTAMP NOT NULL
);

INSERT INTO account (amount, type, effectiveDate) VALUES
  ('10000', 'credit', '2020-03-11 11:59:00');

