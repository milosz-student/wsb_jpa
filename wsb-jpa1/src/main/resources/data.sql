INSERT INTO address (id, address_line1, address_line2, city, postal_code, version)
VALUES (1, 'xx', 'yy', 'city', '62-030', 0);

INSERT INTO address (id, address_line1, address_line2, city, postal_code, version)
VALUES (2, 'aa', 'bb', 'another_city', '00-100', 0);

INSERT INTO address (id, address_line1, address_line2, city, postal_code, version)
VALUES (3, 'cc', 'dd', 'big_city', '00-111', 0);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (1, 'Jan', 'Kowalski', '123456789', 'jan.kowal@wp.com', 'P123', '1985-01-15', 1, True);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (2, 'Ewa', 'Dab', '987654321', 'ewa.dab@onet.com', 'P124', '2010-05-10', 2, False);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_adult)
VALUES (3, 'Marek', 'Kowalak', '111111111', 'marek.kowalak@wp.com', 'P125', '1990-02-16', 3, True);

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (1, 'Alicja', 'Madrowska', '456123789', 'alicja@example.com', 'D567', 'SURGEON', 1);

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (2, 'Krzysztof', 'Lewandowski', '321654987', 'krzysztof123@example.com', 'D568', 'OCULIST', 2);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (1, 'wizyta NFZ', '2024-11-24 10:00:00', 1, 1, 100);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (2, 'wizyta NFZ', '2024-11-25 14:30:00', 2, 2, 200);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (3, 'wizyta prywatna', '2024-12-24 10:00:00', 1, 1, 100);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (4, 'wizyta prywatna', '2024-12-25 14:30:00', 2, 2, 200);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (5, 'wizyta NFZ', '2025-12-30 10:00:00', 2, 1, 100);

INSERT INTO visit (id, description, time, patient_id, doctor_id, price)
VALUES (6, 'wizyta NFZ', '2025-12-31 14:30:00', 2, 2, 200);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (1, 'test1', 'USG', 1);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (2, 'test2', 'RTG', 2);


