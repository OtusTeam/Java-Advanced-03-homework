INSERT INTO my_table (column1, column2, column3, column4)
SELECT
    RANDOM_UUID(), -- Generating a random UUID for column1
    RANDOM_UUID(), -- Generating a random UUID for column2
    RANDOM_UUID(), -- Generating a random UUID for column3
    RANDOM_UUID()  -- Generating a random UUID for column4
FROM
    SYSTEM_RANGE(1, 100000); -- Generating 1000 rows