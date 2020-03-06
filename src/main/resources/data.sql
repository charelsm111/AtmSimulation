INSERT IGNORE INTO account (id, account_number, name, balance, pin) VALUES ('1', '112233', 'Charel Samuel', '300', '123456'), ('2', '112244', 'Jonathan', '300', '123456');

INSERT IGNORE INTO `transaction` (id, type, amount, `date`, destination_account_number, account_id) VALUES
    ('1', 'transfer', '10', '2020-03-06', '112244', '1'),
    ('2', 'transfer', '20', '2020-03-06', '112244', '1'),
    ('3', 'transfer', '100', '2020-03-05', '112233', '2'),
    ('4', 'transfer', '50', '2020-03-05', '112233', '2');

INSERT IGNORE INTO `transaction` (id, type, amount, `date`, account_id) VALUES
    ('5', 'withdraw', '10', '2020-03-06', '1'),
    ('6', 'withdraw', '30', '2020-03-06', '1'),
    ('7', 'withdraw', '10', '2020-03-05', '2'),
    ('8', 'withdraw', '60', '2020-03-05', '2');

