INSERT INTO bank (id_bank) VALUES ('1234');
INSERT INTO office (id_office, id_bank) VALUES ('1234','1234');
INSERT INTO client (client_id, age, name, surname) VALUES ('71034506H', 22, 'Roberto', 'de Castro');
INSERT INTO accounts (id_office, id_bank, account_number) VALUES ('1234','1234','12341234100000000000');
INSERT INTO cards (id, client_id, account_id, pin, type, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, expirationDate, commissionEmission, commissionMaintenance, commissionRenovate) VALUES ('1234011234567892','71034506H', '12341234100000000000','1111', 'CREDIT' ,100.00,1000.00,100.00,1000.00, '04/17',0.0,0.0,0.0);