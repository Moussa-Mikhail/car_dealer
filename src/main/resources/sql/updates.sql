-- checkouts
UPDATE book 
	SET quantity = quantity - 1 
	WHERE book_id = 1 AND quantity > 0;
	
UPDATE book 
	SET quantity = quantity - 1 
	WHERE book_id = 4 AND quantity > 0;

UPDATE book 
	SET quantity = quantity - 1 
	WHERE book_id = 5 AND quantity > 0;


-- returns
UPDATE checkout
	SET return_status_id = 2
	WHERE checkout_id = 2;

UPDATE checkout
	SET return_status_id = 2
	WHERE checkout_id = 3;

UPDATE book 
	SET quantity = quantity + 1 
	WHERE book_id = 4;

UPDATE book 
	SET quantity = quantity + 1 
	WHERE book_id = 5;

-- Late return
UPDATE checkout
	SET return_status_id = 3
    WHERE checkout_id = 1;

UPDATE book
	set quantity = quantity + 1
    WHERE book_id = 1;