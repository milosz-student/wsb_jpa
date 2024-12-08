--- sprawdz czy jest relacja pomiedzy pacjentem a adresem
SELECT
    *
FROM
    PATIENT p
JOIN
    ADDRESS a
ON
    p.ADDRESS_ID = a.ID

--- Jeżeli chcemy sprawdzić do kogo należy dany adres:
--- To nie zadziała bo adres nie ma informacji o doktorze
SELECT * FROM ADDRESS a
INNER JOIN
DOCTOR d ON a.DOCTOR_ID = d.ID;


--- Zapytanie żeby sprawdzić adres doktora:
--- to zadziała bo doktor ma informacje o adresie
SELECT * FROM DOCTOR d
INNER JOIN
    ADDRESS a ON a.ID = d.ADDRESS_ID
--- ten przyklad obrazuje relacje jednokierunkowa


--- Zapytanie żeby wypisać listę doktorów i ich wizyt
SELECT * FROM
    DOCTOR d
LEFT JOIN
    VISIT v ON d.ID = v.DOCTOR_ID

--- Zapytanie żeby wypisać listę wizyt z przypisanymi doktorami
SELECT * FROM
    VISIT v
LEFT JOIN
    DOCTOR d ON v.DOCTOR_ID = d.ID

--- to zadziała bo jest relacja dwukierunkowa

--- zzapytanie żeby wypisać do każdej wizyty listę jego treatments
SELECT * FROM VISIT v
LEFT JOIN MEDICAL_TREATMENT m
ON v.ID = m.VISIT_ID
