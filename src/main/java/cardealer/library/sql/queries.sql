-- All checkouts by member
SELECT first_name, last_name, title
FROM member
LEFT JOIN checkout
ON member.member_id = checkout.member_id
LEFT JOIN book
ON book.book_id = checkout.book_id
ORDER BY checkout.checkout_id;

-- Number of checkouts by member in descending order
Select first_name, last_name, COUNT(*) as num_checkouts
FROM checkout
LEFT JOIN member
ON checkout.member_id = member.member_id
GROUP BY checkout.member_id
ORDER BY num_checkouts DESC;

-- All late checkouts by member
SELECT first_name, last_name, book.title
FROM member
LEFT JOIN checkout
ON member.member_id = checkout.member_id
LEFT JOIN book
ON book.book_id = checkout.book_id
WHERE checkout.return_status_id = 3;

-- All fees
SELECT first_name, last_name, amount
FROM member
RIGHT JOIN fee
ON member.member_id = fee.member_id
ORDER BY member.member_id;

-- Total fees received by member 
SELECT first_name, last_name, SUM(amount) as total_fees
FROM member
LEFT JOIN fee
ON member.member_id = fee.member_id
GROUP BY member.member_id;

-- Same as above but restricted to total_fees > 0
SELECT first_name, last_name, SUM(amount) as total_fees
FROM member
LEFT JOIN fee
ON member.member_id = fee.member_id
GROUP BY member.member_id
HAVING total_fees > 0;


SELECT *
FROM member
LEFT JOIN checkout
ON member.member_id = checkout.member_id
LEFT JOIN book_donation
ON member.member_id = book_donation.member_id
LEFT JOIN room_reservation
ON member.member_id = room_reservation.member_id
LEFT JOIN fee
ON member.member_id = fee.member_id
LEFT JOIN ban
ON member.member_id = ban.member_id;