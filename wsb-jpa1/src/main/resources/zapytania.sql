--- sprawdz czy jest relacja pomiedzy pacjentem a adresem
SELECT
    *
FROM
    PATIENT p
JOIN
    ADDRESS a
ON
    p.ADDRESS_ID = a.ID