INSERT INTO account (id, account_number, name, balance, pin)
SELECT * FROM (SELECT '1', '112233', 'Charel Samuel', '300', '123456') AS tmp
WHERE NOT EXISTS (
    SELECT account_number FROM account WHERE account_number = '112233'
) LIMIT 1;

INSERT INTO account (id, account_number, name, balance, pin)
SELECT * FROM (SELECT '2', '112244', 'Jonathan', '300', '123456') AS tmp
WHERE NOT EXISTS (
    SELECT account_number FROM account WHERE account_number = '112244'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, destination_account_number, account_id)
SELECT * FROM (SELECT '1', 'transfer', '10', '2020-03-06', '112233', '2') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '1'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, destination_account_number, account_id)
SELECT * FROM (SELECT '2', 'transfer', '20', '2020-03-06', '112244', '1') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '2'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, destination_account_number, account_id)
SELECT * FROM (SELECT '3', 'transfer', '100', '2020-03-05', '112233', '2') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '3'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, destination_account_number, account_id)
SELECT * FROM (SELECT '4', 'transfer', '50', '2020-03-05', '112233', '2') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '4'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, account_id)
SELECT * FROM (SELECT '5', 'withdraw', '10', '2020-03-06', '1') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '5'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, account_id)
SELECT * FROM (SELECT '6', 'withdraw', '30', '2020-03-06', '1') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '6'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, account_id)
SELECT * FROM (SELECT '7', 'withdraw', '10', '2020-03-05', '2') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '7'
) LIMIT 1;

INSERT INTO `transaction` (id, type, amount, `date`, account_id)
SELECT * FROM (SELECT '5', 'withdraw', '10', '2020-03-06', '1') AS tmp
WHERE NOT EXISTS (
    SELECT `id` FROM `transaction` WHERE `id` = '5'
) LIMIT 1;

