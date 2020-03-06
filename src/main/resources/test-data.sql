INSERT INTO account (id, account_number, name, balance, pin)
SELECT * FROM (SELECT '5', '11111', 'Andi', '1000', '123456') AS tmp
WHERE NOT EXISTS (
    SELECT account_number FROM account WHERE account_number = '112233'
) LIMIT 1;